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

    @PostMapping("/item")
    public String addItem(@RequestParam(required = false) String name,
                          @RequestParam(required = false) Integer calories,
                          @RequestParam(required = false) Float protein,
                          @RequestParam(required = false) Float carbohydrates,
                          @RequestParam(required = false) Float fats) {
        // Validate parameters before proceeding
        Item newItem = new Item();
        newItem.setName(name);
        newItem.setCalories(calories);
        newItem.setProtein(protein);
        newItem.setCarbohydrates(carbohydrates);
        newItem.setFats(fats);
        dbService.saveItem(newItem); // Assuming you have a saveItem method in your DatabaseService
        return "redirect:/tracker"; // Redirect to the tracker page after adding
    }

    @PostMapping("/{date}/{trackerId}")
    public String deleteTracker(@PathVariable String date, @PathVariable Integer trackerId) {
        dbService.deleteTrackerById(trackerId);
        return "redirect:/tracker/" + date;
    }

    @GetMapping("/items")
    public String listItems(Model model) {
        model.addAttribute("items", dbService.getAllItems());
        return "items";
    }

    @PostMapping("/items")
    public String addItemInItems(@RequestParam(required = false) String name,
                          @RequestParam(required = false) Integer calories,
                          @RequestParam(required = false) Float protein,
                          @RequestParam(required = false) Float carbohydrates,
                          @RequestParam(required = false) Float fats) {
        // Validate parameters before proceeding
        Item newItem = new Item();
        newItem.setName(name);
        newItem.setCalories(calories);
        newItem.setProtein(protein);
        newItem.setCarbohydrates(carbohydrates);
        newItem.setFats(fats);
        dbService.saveItem(newItem); // Assuming you have a saveItem method in your DatabaseService
        return "redirect:/tracker/items"; // Redirect to the tracker page after adding
    }

    @PostMapping("/items/{itemId}")
    public String deleteItem(@PathVariable Integer itemId) {
        dbService.deleteItemById(itemId);
        return "redirect:/tracker/items";
    }

}
