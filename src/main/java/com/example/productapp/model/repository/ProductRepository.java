package com.example.productapp.repository;

import com.example.productapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByPriceBetween(Double min, Double max);
    List<Product> findByQuantityLessThan(int quantity);
    List<Product> findByIsFeaturedTrue();
    List<Product> findByCategoryId(Long categoryId);
    List<Product> findTop5ByOrderByCreatedDateDesc();
}
