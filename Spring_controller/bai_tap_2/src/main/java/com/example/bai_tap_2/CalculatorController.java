package com.example.bai_tap_2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CalculatorController {


    @GetMapping ("/")
    public String index() {
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(@RequestParam (name = "num1") double num1,
                            @RequestParam (name = "num2") double num2,
                            @RequestParam (name = "operation") String operation,
                            Model model) {

        double result = 0;
        String operator = "";

        switch (operation) {
            case "add":
                result = num1 + num2;
                operator = "Addition";
                break;
            case "sub":
                result = num1 - num2;
                operator = "Subtraction";
                break;
            case "mul":
                result = num1 * num2;
                operator = "Multiplication";
                break;
            case "div":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    model.addAttribute("error", "Cannot divide by zero!");
                    return "index";
                }
                operator = "Division";
                break;
        }

        model.addAttribute("result", result);
        model.addAttribute("operation", operator);
        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);
        return "index";
    }
}
