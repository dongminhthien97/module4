package com.example.bai_tap_2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class DictionaryController {
    private static final Map<String, String> dictionary = new HashMap<>();
    static {
        dictionary.put("hello", "xin chào");
        dictionary.put("dog", "con chó");
        dictionary.put("cat", "con mèo");
        dictionary.put("book", "quyển sách");
        dictionary.put("computer", "máy tính");
    }

    @GetMapping("/")
    public String showForm() {
        return "index";
    }

    @PostMapping("/search")
    public String searchWord(@RequestParam("word") String word,
                             Model model) {
        String meaning = dictionary.get(word.toLowerCase());
        model.addAttribute("word", word);
        model.addAttribute("meaning",meaning);
        return "result";
    }
}
