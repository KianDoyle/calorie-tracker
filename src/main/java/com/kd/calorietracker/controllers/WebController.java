package com.kd.calorietracker.controllers;

import com.kd.calorietracker.entities.Item;
import com.kd.calorietracker.entities.Tracker;
import com.kd.calorietracker.services.DatabaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/tracker")
public class WebController {

    private final DatabaseService dbService;

    public WebController(DatabaseService dbService) {
        this.dbService = dbService;
    }

    @GetMapping
    public String homeRedirect() {
        return "redirect:/tracker/" + new Date().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().toString();
    }

    @GetMapping("/{date}")
    public String home(@PathVariable String date, Model model) {
        model.addAttribute("tracker", dbService.getTrackerByDate(date));
        model.addAttribute("items", dbService.getAllItems()); // Fetch items for the dropdown
        model.addAttribute("date", date);
        return "home";
    }


    @PostMapping("/{date}")
    public String homePost(@PathVariable String date, @RequestParam("itemId") Integer itemId, Model model) {
        Item item = dbService.getItemById(itemId); // Fetch the selected item
        dbService.addItem(item);
        return "redirect:/tracker/" + date;
    }


    @DeleteMapping("/{date}")
    public String homeDelete(@PathVariable String date, @RequestBody Tracker tracker, Model model) {
        dbService.deleteTracker(tracker);
        model.addAttribute("tracker", dbService.getTrackerByDate(date));
        return "redirect:home";
    }
}
