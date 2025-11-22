package com.flightapp.service;

import java.time.LocalDate;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;

import com.flightapp.exceptions.ValidationException;
import com.flightapp.exceptions.ResourceNotFoundException;
import com.flightapp.model.FlightInventory;
import com.flightapp.model.TripType;
import com.flightapp.repository.FlightInventoryRepository;
import com.flightapp.request.FlightSearchRequest;

@Service
public class FlightSearchService {

    private final FlightInventoryRepository inventoryRepository;

    public FlightSearchService(FlightInventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Flux<FlightInventory> searchFlights(@Valid FlightSearchRequest req) {

        if (req.getSourceCity() == null || req.getDestinationCity() == null)
            throw new ValidationException("Source and destination are required");

        if (req.getSourceCity().equals(req.getDestinationCity()))
            throw new ValidationException("Source and destination cannot be the same");

        if (req.getTravelDate() == null)
            throw new ValidationException("Travel date is required");

        if (req.getTravelDate().isBefore(LocalDate.now()))
            throw new ValidationException("Travel date cannot be in the past");

        if (req.getTripType() == TripType.ROUND_TRIP) {
            if (req.getReturnDate() == null)
                throw new ValidationException("Return date is required for round-trip");

            if (req.getReturnDate().isBefore(req.getTravelDate()))
                throw new ValidationException("Return date cannot be before travel date");
        }

        return inventoryRepository
                .findBySourceCityAndDestinationCityAndDepartureDate(
                        req.getSourceCity(),
                        req.getDestinationCity(),
                        req.getTravelDate()
                )
                .switchIfEmpty(Flux.error(new ResourceNotFoundException("No outbound flights found")));
    }
}
