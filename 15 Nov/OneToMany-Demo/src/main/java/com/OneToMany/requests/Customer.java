package com.OneToMany.requests;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order1> orders;

    public Long getId() { 
        return id; 
    }

    public void setId(Long id) { 
        this.id = id; 
    }

    public String getName() { 
        return name; 
    }

    public void setName(String name) { 
        this.name = name; 
    }

    public List<Order1> getOrders() { 
        return orders; 
    }

    public void setOrders(List<Order1> orders) {
        this.orders = orders;
        if (orders != null) {
            for (Order1 o : orders) {
                o.setCustomer(this);   // set FK
            }
        }
    }
}
