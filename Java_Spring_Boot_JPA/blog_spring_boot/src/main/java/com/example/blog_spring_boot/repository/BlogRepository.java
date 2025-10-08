package com.example.blog_spring_boot.repository;

import com.example.blog_spring_boot.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class BlogRepository {

    private final IBlogRepository iBlogRepository;

    public BlogRepository(IBlogRepository iBlogRepository) {
        this.iBlogRepository = iBlogRepository;
    }

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
//    public Page<Blog> findAll(Pageable pageable) {
//        return iBlogRepository.findAll(pageable);
//    }
}

