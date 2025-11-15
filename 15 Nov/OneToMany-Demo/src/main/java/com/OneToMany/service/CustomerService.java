package com.OneToMany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.OneToMany.repository.CustomerRepository;
import com.OneToMany.requests.Customer;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository repo;

    public Customer save(Customer customer) {
        return repo.save(customer);
    }

    public Customer get(Long id) {
        return repo.findById(id).orElse(null);
    }
}
