package com.flightapp.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;
import com.flightapp.model.CityEnum;

public class AddInventory {

    @NotNull
    private String airlineId;

    @NotBlank
    private String flightNumber;

    @NotNull
    private CityEnum sourceCity;

    @NotNull
    private CityEnum destinationCity;

    @NotNull
    private LocalDate departureDate;

    @NotNull
    private LocalTime departureTime;

    @NotNull
    private LocalDate arrivalDate;

    @NotNull
    private LocalTime arrivalTime;

    @NotNull
    private Integer totalSeats;

    @NotNull
    private Double price;

    private boolean mealAvailable;

    // Getters + Setters
    public String getAirlineId() { 
    	return airlineId; 
    }
    public void setAirlineId(@NotNull String airlineId) { 
    	this.airlineId = airlineId; 
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

    public Integer getTotalSeats() { 
    	return totalSeats; 
    }
    public void setTotalSeats(Integer totalSeats) { 
    	this.totalSeats = totalSeats; 
    }

    public Double getPrice() { 
    	return price; 
    }
    public void setPrice(Double price) { 
    	this.price = price; 
    }

    public boolean isMealAvailable() { 
    	return mealAvailable; 
    }
    public void setMealAvailable(boolean mealAvailable) { 
    	this.mealAvailable = mealAvailable; 
    }
}
