package com.example.blog_spring_boot.repository;

import com.example.blog_spring_boot.entity.Blog;
import com.example.blog_spring_boot.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBlogRepository extends JpaRepository<Blog, Long> {

    Page<Blog> findAllByCategory(Category category, Pageable pageable);
    Page<Blog> findAllByTitleContaining(String keyword, Pageable pageable);
    List<Blog> findByTitleContainingIgnoreCase(String keyword);
    Page<Blog> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String title, String author, Pageable pageable);
    Page<Blog> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title, String content, Pageable pageable);
}
