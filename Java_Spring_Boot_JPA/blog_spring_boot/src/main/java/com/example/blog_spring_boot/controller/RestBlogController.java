package com.example.blog_spring_boot.controller;

import com.example.blog_spring_boot.entity.Blog;
import com.example.blog_spring_boot.entity.Category;
import com.example.blog_spring_boot.service.IBlogService;
import com.example.blog_spring_boot.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/api")
public class RestBlogController {

    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/blogs")
    public ResponseEntity<Page<Blog>> getBlogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String keyword
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Blog> blogPage;

        if (keyword == null || keyword.trim().isEmpty()) {
            blogPage = blogService.findAll(pageable);
        } else {
            blogPage = blogService.findByTitleContainingOrContentContaining(keyword, keyword, pageable);
        }

        if (blogPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogPage, HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categoriesList = categoryService.findAll();
        if (categoriesList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoriesList, HttpStatus.OK);
    }

    @GetMapping("/categories/{id}/blogs")
    public ResponseEntity<Page<Blog>> getBlogsByCategory(
            @PathVariable Long id,
            @RequestParam(name = "page", defaultValue = "0") int page
    ) {
        Category category = categoryService.findById(id).orElse(null);
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Pageable pageable = PageRequest.of(page, 5);
        Page<Blog> blogPage = blogService.findAllByCategory(category, pageable);
        if (blogPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogPage, HttpStatus.OK);
    }

    @GetMapping("/blogs/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Long id) {
        return blogService.findById(id)
                .map(blog -> new ResponseEntity<>(blog, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/blogs/search")
    public ResponseEntity<Page<Blog>> searchBlogs(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Blog> blogPage = blogService.findByTitleContainingOrContentContaining(keyword, keyword, pageable);
        if (blogPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(blogPage, HttpStatus.OK);
        }
    }
}
