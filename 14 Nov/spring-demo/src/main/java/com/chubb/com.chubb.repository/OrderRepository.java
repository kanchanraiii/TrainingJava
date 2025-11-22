package main.java.com.chubb.com.chubb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chubb.requests.Order1;


@Repository
public interface OrderRepository extends CrudRepository<Order1,Integer> {

}
