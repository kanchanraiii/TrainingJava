package com.flightapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "booking")
public class Booking {

    @Id
    private String bookingId;   

    private TripType tripType;

    @DBRef
    private FlightInventory outboundFlight;

    @DBRef
    private FlightInventory returnFlight;

    private String pnrOutbound;
    private String pnrReturn;

    private String contactName;
    private String contactEmail;

    private int totalPassengers;

    private BookingStatus status;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public TripType getTripType() {
        return tripType;
    }

    public void setTripType(TripType tripType) {
        this.tripType = tripType;
    }

    public FlightInventory getOutboundFlight() {
        return outboundFlight;
    }

    public void setOutboundFlight(FlightInventory outboundFlight) {
        this.outboundFlight = outboundFlight;
    }

    public FlightInventory getReturnFlight() {
        return returnFlight;
    }

    public void setReturnFlight(FlightInventory returnFlight) {
        this.returnFlight = returnFlight;
    }

    public String getPnrOutbound() {
        return pnrOutbound;
    }

    public void setPnrOutbound(String pnrOutbound) {
        this.pnrOutbound = pnrOutbound;
    }

    public String getPnrReturn() {
        return pnrReturn;
    }

    public void setPnrReturn(String pnrReturn) {
        this.pnrReturn = pnrReturn;
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

    public int getTotalPassengers() {
        return totalPassengers;
    }

    public void setTotalPassengers(int totalPassengers) {
        this.totalPassengers = totalPassengers;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }
}
