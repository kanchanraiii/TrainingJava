package com.OneToMany.requests;

import jakarta.persistence.*;

@Entity
public class Order1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String item;
    private double price;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "customer_id")   // Foreign Key
    private Customer customer;

    public Long getId() { 
        return id; 
    }

    public String getItem() { 
        return item; 
    }

    public void setItem(String item) { 
        this.item = item; 
    }

    public double getPrice() { 
        return price; 
    }

    public void setPrice(double price) { 
        this.price = price; 
    }

    public int getQuantity() { 
        return quantity; 
    }

    public void setQuantity(int quantity) { 
        this.quantity = quantity; 
    }

    public Customer getCustomer() { 
        return customer; 
    }

    public void setCustomer(Customer customer) { 
        this.customer = customer; 
    }
}
