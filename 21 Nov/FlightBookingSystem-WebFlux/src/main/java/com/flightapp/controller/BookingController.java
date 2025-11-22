package com.flightapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.flightapp.model.Booking;
import com.flightapp.request.BookingRequest;
import com.flightapp.service.BookingService;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/flight")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // to book a flight by flightID
    @PostMapping("/booking/{flightId}")
    public Mono<Booking> bookFlight(@PathVariable String flightId, @RequestBody BookingRequest req)
	{
		return bookingService.bookFlight(flightId, req);
	}
}


