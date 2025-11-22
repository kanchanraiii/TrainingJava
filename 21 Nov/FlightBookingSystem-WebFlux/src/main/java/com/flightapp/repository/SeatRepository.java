package com.flightapp.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.flightapp.model.Seat;

public interface SeatRepository extends ReactiveMongoRepository<Seat, String> {
}
