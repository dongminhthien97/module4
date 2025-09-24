package com.example.bai_tap_thymeleaf.repository;

import com.example.bai_tap_thymeleaf.enity.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> findAll();
    void save(Product product);
    Product findById(int id);
    void remove(int id);
}
