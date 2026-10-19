package com.smartgrocery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartgrocery.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByName(String name);

    List<Product> findByPrice(double price);

    List<Product> findByQuantity(int quantity);

    List<Product> findByNameAndPrice(String name, double price);

    List<Product> findByNameContainingIgnoreCase(String keyword);

    List<Product> findByPriceLessThan(double price);

    List<Product> findByQuantityLessThan(int quantity);
    
    List<Product> findByPriceGreaterThan(double price);

    boolean existsByName(String name);
}