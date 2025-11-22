package com.flightapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Document(collection = "flight_inventory")
public class FlightInventory {

    @Id
    private String flightId;  

    @DBRef
    private Airline airline;

    private String flightNumber;

    private CityEnum sourceCity;
    private CityEnum destinationCity;

    private LocalDate departureDate;
    private LocalTime departureTime;

    private LocalDate arrivalDate;
    private LocalTime arrivalTime;

    private boolean mealAvailable;

    private int totalSeats;
    private int availableSeats;

    private double price;

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

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

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public boolean isMealAvailable() {
        return mealAvailable;
    }

    public void setMealAvailable(boolean mealAvailable) {
        this.mealAvailable = mealAvailable;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
