package com.flightapp.repository;

import java.time.LocalDate;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.flightapp.model.CityEnum;
import com.flightapp.model.FlightInventory;

public interface FlightInventoryRepository
        extends ReactiveMongoRepository<FlightInventory, String> {

    Flux<FlightInventory> findBySourceCityAndDestinationCityAndDepartureDate(
            CityEnum sourceCity,
            CityEnum destinationCity,
            LocalDate departureDate
    );

    Mono<Boolean> existsByFlightNumberAndDepartureDate(
            String flightNumber,
            LocalDate departureDate
    );
}
