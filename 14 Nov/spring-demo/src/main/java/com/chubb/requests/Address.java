package com.chubb.requests;


import jakarta.validation.constraints.Min;

public class Address {
	
	private String houseNo;
	@Min(value=100000)
	private int pin;
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	

}
