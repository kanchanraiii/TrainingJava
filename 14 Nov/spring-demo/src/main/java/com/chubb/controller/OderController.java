package com.chubb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chubb.requests.Order;
import com.chubb.service.OrderService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class OderController {

    @Autowired
    OrderService service;

    @GetMapping("/order")
    public String getOrder() {
        return "hello";
    }

    @PostMapping("/order")
    public Order saveOrder(@Valid @RequestBody Order order) {
        log.debug("logger added - order received {}", order);
        service.insertOrder(order);
        return order;
    }
}
