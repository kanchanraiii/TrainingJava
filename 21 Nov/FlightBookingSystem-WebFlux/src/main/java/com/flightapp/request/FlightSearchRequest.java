package com.flightapp.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

import com.flightapp.model.CityEnum;
import com.flightapp.model.TripType;

public class FlightSearchRequest {

    @NotNull(message = "Source city is required")
    private CityEnum sourceCity;

    @NotNull(message = "Destination city is required")
    private CityEnum destinationCity;

    @NotNull(message = "Travel date is required")
    private LocalDate travelDate;

    @NotNull(message = "Trip type is required")
    private TripType tripType; 

    private LocalDate returnDate;  // only required for round-trip


    // Getters & Setters
    public CityEnum getSourceCity() {
        return sourceCity;
    }

    public void setSourceCity(CityEnum sourceCity) {
        this.sourceCity = sourceCity;
    }

    public CityEnum getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(CityEnum destinationCity) {
        this.destinationCity = destinationCity;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }

    public TripType getTripType() {
        return tripType;
    }

    public void setTripType(TripType tripType) {
        this.tripType = tripType;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
