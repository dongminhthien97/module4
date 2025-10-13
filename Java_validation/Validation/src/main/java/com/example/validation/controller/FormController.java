package com.example.validation.controller;

import com.example.validation.dto.UserDTO;
import com.example.validation.model.User;
import com.example.validation.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class FormController {

    @Autowired
    private IUserService userService;

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("userDTO", new UserDTO()); 
        return "index";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("userDTO") UserDTO userDTO,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            return "index"; 
        }

        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAge(userDTO.getAge());
        user.setEmail(userDTO.getEmail());

        userService.save(user);

        model.addAttribute("user", user);
        return "result";
    }
}
