package com.example.blog_spring_boot.service;

import com.example.blog_spring_boot.entity.Blog;
import com.example.blog_spring_boot.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IBlogService {
    Page<Blog> findAll(Pageable pageable);
    Page<Blog> findAllByCategory(Category category, Pageable pageable);
    Optional<Blog> findById(Long id);
    void save(Blog blog);
    void deleteById(Long id);
    List<Blog> searchByTitle(String keyword);
    Page<Blog> searchByTitleOrAuthor(String keyword, Pageable pageable);
    Page<Blog> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}
