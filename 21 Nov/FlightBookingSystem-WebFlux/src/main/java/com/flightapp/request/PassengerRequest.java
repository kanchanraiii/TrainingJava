package com.flightapp.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PassengerRequest {

    @NotBlank
    private String name;

    @NotNull
    private Integer age;

    @NotBlank(message="Passenger Age is a required field")
    private String gender;

    private String seatOutbound;
    private String seatReturn;

    private String meal;

    // Getters + Setters
    public String getName() { 
    	return name; 
    }
    public void setName(String name) { 
    	this.name = name; 
    }

    public Integer getAge() { 
    	return age; 
    }
    public void setAge(Integer age) { 
    	this.age = age; 
    }

    public String getGender() { 
    	return gender; 
    }
    public void setGender(String gender) { 
    	this.gender = gender; 
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

    public String getMeal() { 
    	return meal; 
    }
    public void setMeal(String meal) { 
    	this.meal = meal; 
    }
}
