package com.flightapp.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.flightapp.model.Passenger;

public interface PassengerRepository extends ReactiveMongoRepository<Passenger, String> {
}
