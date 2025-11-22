package com.flightapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.flightapp.model.FlightInventory;
import com.flightapp.request.FlightSearchRequest;
import com.flightapp.service.FlightSearchService;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/flight")
public class FlightSearchController {

    @Autowired
    private FlightSearchService searchService;

    @PostMapping("/search")
    public Flux<FlightInventory> searchFlights(@Valid @RequestBody FlightSearchRequest req) {
        return searchService.searchFlights(req);
    }
}
