package com.caching.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.caching.model.Product;
import com.caching.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    
    @Cacheable(value = "products", key = "#id")
    public Product getProduct(String id) { 
        System.out.println("\nMongoDB HIT: Fetching Product " + id + " from the MongoDB Database\n");
        
     
        return repository.findById(id).orElse(null); 
    }

    
    @CachePut(value = "products", key = "#product.id")
    public Product updateProduct(Product product) {
        System.out.println("SERVICE: Updating Product " + product.getId() + " in DB & Cache");
        return repository.save(product);
    }

   
    @CacheEvict(value = "products", key = "#id")
    public void deleteProduct(String id) { // ID is now String
        System.out.println("SERVICE: Deleting Product " + id + " from DB & Cache");
        repository.deleteById(id);
    }
}