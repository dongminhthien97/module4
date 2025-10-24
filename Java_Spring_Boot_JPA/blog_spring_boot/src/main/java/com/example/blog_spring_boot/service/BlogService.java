package com.example.blog_spring_boot.service;
import com.example.blog_spring_boot.entity.Blog;
import com.example.blog_spring_boot.entity.Category;
import com.example.blog_spring_boot.repository.BlogRepository;
import com.example.blog_spring_boot.repository.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService implements IBlogService {

    @Autowired
    private IBlogRepository blogRepository;

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Page<Blog> findAllByCategory(Category category, Pageable pageable) {
        return blogRepository.findAllByCategory(category, pageable);
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return blogRepository.findById(id);
    }

    @Override
    public void save(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    public void deleteById(Long id) {
        blogRepository.deleteById(id);
    }
    @Override
    public List<Blog> searchByTitle(String keyword) {
        if(keyword == null || keyword.trim().isEmpty()) {
            return blogRepository.findAll();
        }
        return blogRepository.findByTitleContainingIgnoreCase(keyword);
    }
    @Override
    public Page<Blog> searchByTitleOrAuthor(String keyword, Pageable pageable) {
        return blogRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(keyword, keyword, pageable);
    }

    @Override
    public Page<Blog> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable) {
        return blogRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(title, content, pageable);
    }


}

