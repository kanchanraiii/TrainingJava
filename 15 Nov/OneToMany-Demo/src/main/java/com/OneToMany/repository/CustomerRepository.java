package com.OneToMany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.OneToMany.requests.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
