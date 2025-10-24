package com.example.blog_spring_boot.controller;

import com.example.blog_spring_boot.entity.Blog;
import com.example.blog_spring_boot.entity.Category;
import com.example.blog_spring_boot.service.IBlogService;
import com.example.blog_spring_boot.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/")
    public String redirectToBlogs() {
        return "redirect:/blogs";
    }

    @GetMapping
    public String listBlogs(@RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "5") int size,
                            @RequestParam(defaultValue = "createdAt") String sortBy,
                            Model model) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        Page<Blog> blogs = blogService.findAll(pageable);

        model.addAttribute("blogs", blogs);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", blogs.getTotalPages());
        model.addAttribute("categories", categoryService.findAll());

        return "blog/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("categories", categoryService.findAll());
        return "blog/create";
    }

    @PostMapping("/create")
    public String saveBlog(@ModelAttribute Blog blog, RedirectAttributes redirectAttributes) {
        if (blog.getCategory() != null && blog.getCategory().getId() != null) {
            Category category = categoryService.findById(blog.getCategory().getId()).orElse(null);
            blog.setCategory(category);
        }

        blogService.save(blog);
        redirectAttributes.addFlashAttribute("successMessage", " Blog đã được tạo thành công!");
        return "redirect:/blogs";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isEmpty()) {
            return "redirect:/blogs";
        }
        model.addAttribute("blog", blog.get());
        model.addAttribute("categories", categoryService.findAll());
        return "blog/edit";
    }

    @PostMapping("/edit")
    public String updateBlog(@ModelAttribute Blog blog) {
        if (blog.getCategory() != null && blog.getCategory().getId() != null) {
            Category category = categoryService.findById(blog.getCategory().getId()).orElse(null);
            blog.setCategory(category);
        }
        blogService.save(blog);
        return "redirect:/blogs";
    }

    @GetMapping("/{id}")
    public String viewBlog(@PathVariable Long id, Model model) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isEmpty()) {
            return "redirect:/blogs";
        }
        model.addAttribute("blog", blog.get());
        return "blog/detail";
    }

    @GetMapping("/delete/{id}")
    public String deleteBlog(@PathVariable Long id) {
        blogService.deleteById(id);
        return "redirect:/blogs";
    }

    @GetMapping("/category/{id}")
    public String filterByCategory(@PathVariable Long id,
                                   @RequestParam(defaultValue = "0") int page,
                                   Model model) {
        Optional<Category> category = categoryService.findById(id);
        if (category.isEmpty()) {
            return "redirect:/blogs";
        }

        Pageable pageable = PageRequest.of(page, 5, Sort.by("createdAt").descending());
        Page<Blog> blogs = blogService.findAllByCategory(category.get(), pageable);

        model.addAttribute("blogs", blogs);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("selectedCategory", category.get());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", blogs.getTotalPages());

        return "blog/list";
    }
}
