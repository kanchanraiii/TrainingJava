package com.chubb.service;

import org.springframework.stereotype.Service;

import com.chubb.requests.Order;

import jakarta.validation.Valid;

@Service
public class OrderService {
	

	public void insertOrder(@Valid Order order) {
		// TODO Auto-generated method stub
		System.out.print(order);
		
	}

}
