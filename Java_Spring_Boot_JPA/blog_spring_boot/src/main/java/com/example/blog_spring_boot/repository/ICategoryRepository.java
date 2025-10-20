package com.example.blog_spring_boot.repository;

import com.example.blog_spring_boot.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<Category , Long> {
    Optional<Category> findById(Long id);
}
