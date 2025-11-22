package com.flightapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.flightapp.model.Booking;
import com.flightapp.service.BookingService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/flight")
public class TicketController {

    @Autowired
    private BookingService bookingService;

    
    @GetMapping("/ticket/{pnr}")
    public Mono<Booking> getTicket(@PathVariable String pnr) {
        return bookingService.getTicket(pnr);
    }

    @GetMapping("/booking/history/{email}")
    public Flux<Booking> getHistory(@PathVariable String email) {
        return bookingService.getHistory(email);
    }

    @DeleteMapping("/booking/cancel/{pnr}")
    public Mono<String> cancelTicket(@PathVariable String pnr) {
        return Mono.fromRunnable(() -> bookingService.cancelTicket(pnr))
                .then(Mono.just("Booking cancelled successfully"));
    }

}
