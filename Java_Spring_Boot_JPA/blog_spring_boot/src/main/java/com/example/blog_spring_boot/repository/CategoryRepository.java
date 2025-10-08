package com.example.blog_spring_boot.repository;

import org.springframework.stereotype.Repository;
import com.example.blog_spring_boot.entity.Category;

@Repository
public class CategoryRepository {
    private final ICategoryRepository iCategoryRepository;

    public CategoryRepository(ICategoryRepository iCategoryRepository) {
        this.iCategoryRepository = iCategoryRepository;
    }

    public Iterable findAll() {
        return iCategoryRepository.findAll();
    }

    public Category findById(Long id) {
        return iCategoryRepository.findById(id).orElse(null);
    }

    public Category save(Category category) {
        return iCategoryRepository.save(category);
    }

    public Category deleteById(Long id) {
        Category category = findById(id);
        if (category != null) {
            iCategoryRepository.deleteById(id);
        }
        return category;
    }
}
