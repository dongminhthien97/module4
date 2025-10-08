package com.example.blog_spring_boot.controller;

import com.example.blog_spring_boot.entity.Blog;
import com.example.blog_spring_boot.entity.Category;
import com.example.blog_spring_boot.service.IBlogService;
import com.example.blog_spring_boot.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

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

    @Controller
    public class HomeController {
        @GetMapping("/")
        public String home() {
            return "redirect:/blogs";
        }
    }

    @GetMapping("/blogs")
    public String listBlogs(Model model, @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Blog> blogs = blogService.findAll(pageable);
        model.addAttribute("blogs", blogs);
        return "blog/list";
        }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("categories", categoryService.findAll());
        return "blog/create";
    }

    @PostMapping("/create")
    public String saveBlog(@ModelAttribute("blog") Blog blog) {
        blogService.save(blog);
        return "redirect:/blogs";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isEmpty()) {
            return "redirect:/error";
        }
        model.addAttribute("blog", blog.get());
        model.addAttribute("categories", categoryService.findAll());
        return "blog/edit";
    }

    @PostMapping("/edit")
    public String updateBlog(@ModelAttribute("blog") Blog blog) {
        blogService.save(blog);
        return "redirect:/blogs";
    }

    @GetMapping("/{id}")
    public String viewBlog(@PathVariable Long id, Model model) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isEmpty()) {
            return "redirect:/error";
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
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", blogs.getTotalPages());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("selectedCategory", category.get());

        return "blog/list";
    }
}

//package com.example.blog_spring_boot.controller;
//
//import com.example.blog_spring_boot.entity.Blog;
//import com.example.blog_spring_boot.service.IBlogService;
//import com.example.blog_spring_boot.service.ICategoryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import java.util.Optional;
//
//@Controller
//@RequestMapping("/blogs")
//public class BlogController {
//
//    @Autowired
//    private IBlogService blogService;
//
//    @Autowired
//    private ICategoryService categoryService;
//
//    @GetMapping
//    public String listBlogs(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "5") int size,
//            Model model) {
//
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Blog> blogs = blogService.findAll(pageable);
//
//        model.addAttribute("blogs", blogs);
//        model.addAttribute("currentPage", page);
//        model.addAttribute("totalPages", blogs.getTotalPages());
//
//        return "blog/list";
//    }
//
//
//    @GetMapping("/create")
//    public String showCreateForm(Model model) {
//        model.addAttribute("blog", new Blog());
//        model.addAttribute("categories", categoryService.findAll());
//        return "blog/create";
//    }
//
//    @PostMapping("/create")
//    public String saveBlog(@ModelAttribute("blog") Blog blog) {
//        blogService.save(blog);
//        return "redirect:/blogs";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String showEditForm(@PathVariable Long id, Model model) {
//        Optional<Blog> blog = blogService.findById(id);
//        if (blog.isPresent()) {
//            model.addAttribute("blog", blog.get());
//            model.addAttribute("categories", categoryService.findAll());
//            return "blog/edit";
//        } else {
//            return "redirect:/blogs";
//        }
//    }
//
//    @PostMapping("/edit")
//    public String updateBlog(@ModelAttribute("blog") Blog blog) {
//        blogService.save(blog);
//        return "redirect:/blogs";
//    }
//
//    @GetMapping("/detail/{id}")
//    public String viewDetail(@PathVariable Long id, Model model) {
//        Optional<Blog> blog = blogService.findById(id);
//        if (blog.isPresent()) {
//            model.addAttribute("blog", blog.get());
//            return "blog/detail";
//        } else {
//            return "redirect:/blogs";
//        }
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteBlog(@PathVariable Long id) {
//        blogService.deleteById(id);
//        return "redirect:/blogs";
//    }


