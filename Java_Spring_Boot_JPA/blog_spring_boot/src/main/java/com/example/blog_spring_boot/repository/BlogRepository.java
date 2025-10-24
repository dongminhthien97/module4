package com.example.blog_spring_boot.repository;

import com.example.blog_spring_boot.entity.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BlogRepository {

    @Autowired
    private IBlogRepository iBlogRepository;

    public Blog save(Blog blog) {
        return iBlogRepository.save(blog);
    }

    public void deleteById(Long id) {
        iBlogRepository.deleteById(id);
    }

    public Blog findById(Long id) {
        return iBlogRepository.findById(id).orElse(null);
    }

    public Iterable<Blog> findAll() {
        return iBlogRepository.findAll();
    }
    public List<Blog> findByTitleContainingIgnoreCase(String keyword) {
        return iBlogRepository.findByTitleContainingIgnoreCase(keyword);
    }
    public Page<Blog> findAll(Pageable pageable) {
        return iBlogRepository.findAll(pageable);
    }

    public Page<Blog> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title, String content, Pageable pageable) {
        return iBlogRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(title, content, pageable);
    }
}

