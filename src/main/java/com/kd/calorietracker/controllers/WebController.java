package com.kd.calorietracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tracker")
public class WebController {

    private final Service service;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("tracker", service.getTracker());
        return "home";
    }

    @PostMapping
    public String homePost(@RequestBody Food food, Model model) {
        service.addFood(food);
        model.addAttribute("tracker", service.getTracker());
        return "redirect:home";
    }
}
