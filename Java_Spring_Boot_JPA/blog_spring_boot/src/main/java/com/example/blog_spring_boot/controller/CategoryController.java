package com.example.blog_spring_boot.controller;


import com.example.blog_spring_boot.entity.Category;
import com.example.blog_spring_boot.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "category/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/create";
    }

    @PostMapping("/create")
    public String saveCategory(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Category> category = categoryService.findById(id);
        if (category.isEmpty()) {
            return "redirect:/categories";
        }
        model.addAttribute("category", category.get());
        return "category/edit";
    }

    @GetMapping("/detail/{id}")
    public String viewDetail(@PathVariable Long id, Model model) {
        return categoryService.findById(id)
                .map(category -> {
                    model.addAttribute("category", category);
                    return "category/categories-detail";
                })
                .orElse("redirect:/categories");
    }

    @PostMapping("/edit")
    public String updateCategory(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
        return "redirect:/categories";
    }
}
