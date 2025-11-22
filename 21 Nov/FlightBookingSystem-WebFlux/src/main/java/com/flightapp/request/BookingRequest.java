package com.flightapp.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

import com.flightapp.model.TripType;

public class BookingRequest {

    @NotNull
    @NotNull
    private String outboundFlightId;

    private String returnFlightId;


    @NotBlank(message="Contact name is a required field")
    private String contactName;

    @Email(message="Invalid email format")
    @NotBlank(message="Email is required field")
    private String contactEmail;
    
    @NotNull(message = "Trip type is required")
    private TripType tripType;


    public TripType getTripType() {
		return tripType;
	}
	public void setTripType(TripType tripType) {
		this.tripType = tripType;
	}
	@Valid
    @NotNull
    private List<PassengerRequest> passengers;

    // Getters + Setters
    public @NotNull @NotNull String getOutboundFlightId() { 
    	return outboundFlightId; 
    }
    public void setOutboundFlightId(@NotNull @NotNull String outboundFlightId) { 
    	this.outboundFlightId = outboundFlightId; 
    }

    public String getReturnFlightId() { 
    	return returnFlightId; 
    }
    public void setReturnFlightId(String returnFlightId) { 
    	this.returnFlightId = returnFlightId; 
    }

    public String getContactName() { 
    	return contactName; 
    }
    public void setContactName(String contactName) { 
    	this.contactName = contactName; 
    }

    public String getContactEmail() { 
    	return contactEmail; 
    }
    public void setContactEmail(String contactEmail) { 
    	this.contactEmail = contactEmail; 
    }

    public List<PassengerRequest> getPassengers() { 
    	return passengers; 
    }
    public void setPassengers(List<PassengerRequest> passengers) { 
    	this.passengers = passengers; 
    }
}
