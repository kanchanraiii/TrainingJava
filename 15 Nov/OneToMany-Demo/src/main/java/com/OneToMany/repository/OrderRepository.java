package com.OneToMany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.OneToMany.requests.Order1;

public interface OrderRepository extends JpaRepository<Order1, Long> {
}
