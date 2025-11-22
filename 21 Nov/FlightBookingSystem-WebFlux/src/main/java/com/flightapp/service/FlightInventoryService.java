package com.flightapp.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.flightapp.model.FlightInventory;
import com.flightapp.model.Airline;
import com.flightapp.exceptions.ResourceNotFoundException;
import com.flightapp.exceptions.ValidationException;
import com.flightapp.repository.FlightInventoryRepository;
import com.flightapp.repository.AirlineRepository;
import com.flightapp.request.AddInventory;

@Service
public class FlightInventoryService {

    @Autowired
    private FlightInventoryRepository inventoryRepository;

    @Autowired
    private AirlineRepository airlineRepository;

    public Mono<FlightInventory> addInventory(AddInventory req) {

        if (req.getFlightNumber() == null || req.getFlightNumber().isBlank())
            throw new ValidationException("Flight number is required");

        if (req.getSourceCity() == null || req.getDestinationCity() == null)
            throw new ValidationException("Source and destination are required");

        if (req.getSourceCity().equals(req.getDestinationCity()))
            throw new ValidationException("Source and destination cannot be same");

        if (req.getTotalSeats() == null || req.getTotalSeats() <= 0)
            throw new ValidationException("Total seats must be greater than zero");

        if (req.getPrice() == null || req.getPrice() <= 0)
            throw new ValidationException("Price must be greater than zero");

        if (req.getDepartureDate() == null || req.getArrivalDate() == null)
            throw new ValidationException("Dates are required");

        if (req.getDepartureTime() == null || req.getArrivalTime() == null)
            throw new ValidationException("Times are required");

        LocalDate departureDate = req.getDepartureDate();
        LocalDate arrivalDate = req.getArrivalDate();
        LocalTime departureTime = req.getDepartureTime();
        LocalTime arrivalTime = req.getArrivalTime();

        LocalDateTime depDT = LocalDateTime.of(departureDate, departureTime);
        LocalDateTime arrDT = LocalDateTime.of(arrivalDate, arrivalTime);

        if (!depDT.isAfter(LocalDateTime.now()))
            throw new ValidationException("Departure must be in the future");

        if (!arrDT.isAfter(depDT))
            throw new ValidationException("Arrival must be after departure");

        return airlineRepository.findById(req.getAirlineId())
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Airline not found")))
                .flatMap(airline ->
                        inventoryRepository
                                .existsByFlightNumberAndDepartureDate(req.getFlightNumber(), departureDate)
                                .flatMap(exists -> {
                                    if (exists)
                                        return Mono.error(new ValidationException("Flight already exists on this date"));

                                    FlightInventory inv = new FlightInventory();
                                    inv.setAirline(airline);
                                    inv.setFlightNumber(req.getFlightNumber());
                                    inv.setSourceCity(req.getSourceCity());
                                    inv.setDestinationCity(req.getDestinationCity());
                                    inv.setDepartureDate(departureDate);
                                    inv.setDepartureTime(departureTime);
                                    inv.setArrivalDate(arrivalDate);
                                    inv.setArrivalTime(arrivalTime);
                                    inv.setTotalSeats(req.getTotalSeats());
                                    inv.setAvailableSeats(req.getTotalSeats());
                                    inv.setMealAvailable(req.isMealAvailable());
                                    inv.setPrice(req.getPrice());

                                    return inventoryRepository.save(inv);
                                })
                );
    }
}
