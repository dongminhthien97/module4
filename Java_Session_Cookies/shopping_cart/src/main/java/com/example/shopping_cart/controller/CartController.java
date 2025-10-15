package com.example.shopping_cart.controller;

import com.example.shopping_cart.model.Cart;
import com.example.shopping_cart.model.Product;
import com.example.shopping_cart.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final ProductService productService;

    public CartController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showCart(HttpSession session, Model model) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        model.addAttribute("cart", cart);
        return "cart";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, HttpSession session) {
        Product product = productService.findById(id);
        if (product == null) return "redirect:/";

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) cart = new Cart();

        cart.addProduct(product);
        session.setAttribute("cart", cart);

        return "redirect:/cart";
    }
    @GetMapping("/remove/{id}")
    public String removeFromCart(@PathVariable Long id, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            Product product = productService.findById(id);
            if (product != null) {
                cart.removeProduct(product);
                session.setAttribute("cart", cart);
            }
        }
        return "redirect:/cart";
    }
    @PostMapping("/update")
    public String updateCartQuantity(@RequestParam("productId") Long productId,
                                     @RequestParam("quantity") Integer quantity,
                                     HttpSession session) {
        if (quantity == null) quantity = 1;
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            Product product = productService.findById(productId);
            if (product != null) {
                cart.updateProductQuantity(product, quantity);
                session.setAttribute("cart", cart);
            }
        }
        return "redirect:/cart";
    }
    @GetMapping("/checkout")
    public String showCheckout(HttpSession session, Model model) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.countItemQuantity() == 0) {
            return "redirect:/cart";
        }
        model.addAttribute("cart", cart);
        model.addAttribute("fullname", "");
        model.addAttribute("phone", "");
        model.addAttribute("address", "");
        return "checkout";
    }
    @PostMapping("/checkout")
    public String doCheckout(@RequestParam String fullname,
                             @RequestParam String phone,
                             @RequestParam String address,
                             HttpSession session,
                             Model model) {

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.countItemQuantity() == 0) {
            return "redirect:/cart";
        }
        model.addAttribute("fullname", fullname);
        model.addAttribute("phone", phone);
        model.addAttribute("address", address);
        model.addAttribute("total", cart.countTotalPayment());

        session.removeAttribute("cart");

        return "checkout-success";
    }

    @PostMapping("/cart/update")
    public String updateQuantity(@RequestParam("id") Long productId,
                                 @RequestParam("quantity") int quantity,
                                 @ModelAttribute("cart") Cart cart) {
        cart.updateProduct(productId, quantity);
        return "redirect:/cart";
    }
}

