package com.chubb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chubb.repository.OrderRepository;
import com.chubb.requests.Order1;

import jakarta.validation.Valid;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public Order1 insertOrder(@Valid Order1 order) {
        return orderRepository.save(order);
    }
}
