package com.flightapp.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.flightapp.model.Airline;

public interface AirlineRepository extends ReactiveMongoRepository<Airline, String> {
}
