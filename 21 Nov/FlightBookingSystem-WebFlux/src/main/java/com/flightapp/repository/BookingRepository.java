package com.flightapp.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.flightapp.model.Booking;

public interface BookingRepository extends ReactiveMongoRepository<Booking, String> {

    Mono<Booking> findByPnrOutbound(String pnr);
    Mono<Booking> findByPnrReturn(String pnr);

    Flux<Booking> findByContactEmail(String email);
}
