package com.example.bai_tap_thymeleaf.controller;

import com.example.bai_tap_thymeleaf.entity.Product;
import com.example.bai_tap_thymeleaf.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "product/list";
    }

    @GetMapping("/add")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("product/add");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/add")
    public String createProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "product/edit";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute Product product) {
        productService.update(product.getId(), product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable("id") int id) {
        productService.remove(id);
        return "redirect:/products";
    }

    @GetMapping("/{id}/detail")
    public String viewProduct(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "product/detail";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam ("name") String name, Model model) {
        List<Product> searchResults = productService.findAll().stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
        model.addAttribute("products", searchResults);
        return "product/list";
    }
}
