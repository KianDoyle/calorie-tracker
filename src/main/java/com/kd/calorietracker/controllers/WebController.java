package com.kd.calorietracker.controllers;

import com.kd.calorietracker.services.ApiService;
import com.kd.calorietracker.services.DatabaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tracker")
public class WebController {

    private final DatabaseService dbService;
    private final ApiService apiService;

    public WebController(DatabaseService dbService, ApiService apiService) {
        this.dbService = dbService;
        this.apiService = apiService;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("tracker", dbService.getTracker());
        return "home";
    }

    @PostMapping
    public String homePost(@RequestBody Food food, Model model) {
        dbService.addFood(food);
        model.addAttribute("tracker", dbService.getTracker());
        return "redirect:home";
    }
}
