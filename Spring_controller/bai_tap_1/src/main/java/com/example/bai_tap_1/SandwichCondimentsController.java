package com.example.bai_tap_1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SandwichCondimentsController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
    @RequestMapping("/save")
    public String save(
            @RequestParam(value = "condiments", required = false) String[] condiments,
            Model model) {
        model.addAttribute("condiments", condiments);
        return "result";
    }
}
