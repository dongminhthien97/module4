package com.example.bai_tap_1.controller;


import com.example.bai_tap_1.enity.MailSetting;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/settings")
public class MailSettingController {

    @GetMapping("/")
    public String index() {
        return "settings";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showForm(Model model) {
        model.addAttribute("settings", new MailSetting());
        model.addAttribute("languages", new String[]{"English", "Vietnamese", "Japanese", "Chinese"});
        model.addAttribute("pageSizes", new int[]{5, 10, 15, 25, 50, 100});
        return "settings";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("settings") MailSetting settings, Model model) {
        model.addAttribute("settings", settings);
        return "result";
    }
}
