package com.flightapp.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

import com.flightapp.exceptions.ResourceNotFoundException;
import com.flightapp.exceptions.ValidationException;

import com.flightapp.model.Booking;
import com.flightapp.model.BookingStatus;
import com.flightapp.model.FlightInventory;
import com.flightapp.model.Passenger;
import com.flightapp.model.TripType;
import com.flightapp.model.MealType;

import com.flightapp.repository.BookingRepository;
import com.flightapp.repository.FlightInventoryRepository;
import com.flightapp.repository.PassengerRepository;

import com.flightapp.request.BookingRequest;
import com.flightapp.request.PassengerRequest;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FlightInventoryRepository inventoryRepository;

    @Autowired
    private PassengerRepository passengerRepository;


   // helper methods for validation
    private void validatePassengersExist(BookingRequest req) {
        if (req.getPassengers() == null || req.getPassengers().isEmpty()) {
            throw new ValidationException("At least one passenger is required");
        }
    }

    private void validateTripType(BookingRequest req) {
        if (req.getTripType() == TripType.ROUND_TRIP &&
                req.getReturnFlightId() == null) {
            throw new ValidationException("Return flight ID is required for round trip");
        }
    }

    private void validateSeatAvailability(FlightInventory flight, int count, String type) {
        if (flight.getAvailableSeats() < count) {
            throw new ValidationException("Not enough seats available on " + type + " flight");
        }
    }

    private void validatePassengerFields(PassengerRequest p, boolean roundTrip) {

        if (p.getAge() <= 0) {
            throw new ValidationException("Passenger age must be greater than 0");
        }

        if (p.getSeatOutbound() == null || p.getSeatOutbound().isBlank()) {
            throw new ValidationException("seatOutbound is required");
        }

        if (roundTrip &&
                (p.getSeatReturn() == null || p.getSeatReturn().isBlank())) {
            throw new ValidationException("seatReturn is required for round trip");
        }

        if (p.getMeal() != null) {
            try {
                MealType.valueOf(p.getMeal().toUpperCase());
            } catch (Exception e) {
                throw new ValidationException("Invalid meal type: " + p.getMeal());
            }
        }
    }


    
    // book a flight
    public Mono<Booking> bookFlight(String flightId, BookingRequest req) {

        validatePassengersExist(req);
        validateTripType(req);

        int passengerCount = req.getPassengers().size();

        @SuppressWarnings("null")
		Mono<FlightInventory> outboundMono =
                inventoryRepository.findById(req.getOutboundFlightId())
                    .switchIfEmpty(Mono.error(new ResourceNotFoundException("Outbound flight not found")));

        Mono<FlightInventory> returnMono =
                (req.getReturnFlightId() == null)
                    ? Mono.empty()
                    : inventoryRepository.findById(req.getReturnFlightId())
                        .switchIfEmpty(Mono.error(new ResourceNotFoundException("Return flight not found")));

        // generate PNR
        String pnr = UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 6)
                .toUpperCase();

        return outboundMono.flatMap(outbound -> {

            validateSeatAvailability(outbound, passengerCount, "outbound");

            return returnMono.defaultIfEmpty(null).flatMap(returning -> {

                if (returning != null) {
                    validateSeatAvailability(returning, passengerCount, "return");
                }

                Booking booking = new Booking();
                booking.setOutboundFlight(outbound);
                booking.setReturnFlight(returning);
                booking.setContactName(req.getContactName());
                booking.setContactEmail(req.getContactEmail());
                booking.setTotalPassengers(passengerCount);
                booking.setStatus(BookingStatus.CONFIRMED);
                booking.setPnrOutbound(pnr);

                return bookingRepository.save(booking)
                        .flatMap(savedBooking -> {

                            boolean isRoundTrip = (returning != null);

                            // Save passengers
                            Flux<Passenger> passengerFlux = Flux.fromIterable(req.getPassengers())
                                    .flatMap(p -> {

                                        validatePassengerFields(p, isRoundTrip);

                                        Passenger ps = new Passenger();
                                        ps.setName(p.getName());
                                        ps.setAge(p.getAge());
                                        ps.setGender(p.getGender());
                                        ps.setSeatOutbound(p.getSeatOutbound());
                                        ps.setSeatReturn(p.getSeatReturn());

                                        if (p.getMeal() != null) {
                                            ps.setMeal(MealType.valueOf(p.getMeal().toUpperCase()));
                                        }

                                        ps.setBooking(savedBooking);
                                        return passengerRepository.save(ps);
                                    });

                            // Update outbound seats
                            outbound.setAvailableSeats(outbound.getAvailableSeats() - passengerCount);
                            Mono<FlightInventory> outboundSave = inventoryRepository.save(outbound);

                            // Update return seats (reactive + correct)
                            Mono<FlightInventory> returnSave =
                                    (returning != null)
                                        ? Mono.defer(() -> {
                                            returning.setAvailableSeats(
                                                returning.getAvailableSeats() - passengerCount
                                            );
                                            return inventoryRepository.save(returning);
                                        })
                                        : Mono.empty();

                           
                            return passengerFlux
                                    .then(outboundSave)
                                    .then(returnSave)
                                    .thenReturn(savedBooking);
                        });
            });
        });
    }


   
    // get ticker by pnr
    public Mono<Booking> getTicket(String pnr) {
        return bookingRepository.findByPnrOutbound(pnr)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("PNR not found")));
    }


  
    // cancel a ticket
    public Mono<Void> cancelTicket(String pnr) {

        return getTicket(pnr).flatMap(booking -> {

            if (booking.getStatus() == BookingStatus.CANCELLED) {
                return Mono.error(new ValidationException("Ticket is already cancelled"));
            }

            booking.setStatus(BookingStatus.CANCELLED);

            
            FlightInventory out = booking.getOutboundFlight();
            out.setAvailableSeats(out.getAvailableSeats() + booking.getTotalPassengers());
            Mono<FlightInventory> outSave = inventoryRepository.save(out);

           
            Mono<FlightInventory> returnSave =
                    (booking.getReturnFlight() != null)
                        ? Mono.defer(() -> {
                            FlightInventory ret = booking.getReturnFlight();
                            ret.setAvailableSeats(
                                ret.getAvailableSeats() + booking.getTotalPassengers()
                            );
                            return inventoryRepository.save(ret);
                        })
                        : Mono.empty();

            // save booking + save flights
            return outSave
                    .then(returnSave)
                    .then(bookingRepository.save(booking))
                    .then();
        });
    }


   // history of bookings by email
    public Flux<Booking> getHistory(String email) {
        return bookingRepository.findByContactEmail(email)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("No bookings found")));
    }
}
