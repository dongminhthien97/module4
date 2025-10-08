package com.example.blog_spring_boot.service;

import com.example.blog_spring_boot.entity.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> findAll();
    Optional<Category> findById(Long id);
    void save(Category category);
    void deleteById(Long id);
}
