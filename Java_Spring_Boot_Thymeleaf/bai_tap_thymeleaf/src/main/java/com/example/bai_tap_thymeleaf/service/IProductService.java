package com.example.bai_tap_thymeleaf.service;

import com.example.bai_tap_thymeleaf.enity.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    void save(Product product);
    Product findById(int id);
    void update(int id, Product product);
    void remove(int id);
}
