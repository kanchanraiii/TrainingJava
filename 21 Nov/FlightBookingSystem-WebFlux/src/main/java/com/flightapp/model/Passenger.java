package com.flightapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "passenger")
public class Passenger {

    @Id
    private String passengerId;   

    @DBRef
    private Booking booking;

    private String name;
    private int age;
    private String gender;

    private MealType meal;

    private String seatOutbound;
    private String seatReturn;

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public MealType getMeal() {
        return meal;
    }

    public void setMeal(MealType meal) {
        this.meal = meal;
    }

    public String getSeatOutbound() {
        return seatOutbound;
    }

    public void setSeatOutbound(String seatOutbound) {
        this.seatOutbound = seatOutbound;
    }

    public String getSeatReturn() {
        return seatReturn;
    }

    public void setSeatReturn(String seatReturn) {
        this.seatReturn = seatReturn;
    }
}
