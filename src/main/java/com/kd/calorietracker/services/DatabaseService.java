package com.kd.calorietracker.services;

import com.kd.calorietracker.entities.Item;
import com.kd.calorietracker.entities.Tracker;
import com.kd.calorietracker.repositories.ItemRepository;
import com.kd.calorietracker.repositories.TrackerRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DatabaseService {

    private final TrackerRepository trackerRepository;
    private final ItemRepository itemRepository;

    public DatabaseService(TrackerRepository trackerRepository, ItemRepository itemRepository) {
        this.trackerRepository = trackerRepository;
        this.itemRepository = itemRepository;
    }

    public List<Tracker> getTrackerByDate(String date) {
        return trackerRepository.findAll().stream().filter(tracker -> tracker.getDate().toString().equals(date)).toList();
    }

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public void addItem(Item item) {
        Tracker tracker = new Tracker();
        tracker.setItem(item);
        tracker.setDate(new Date().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
        tracker.setTime(java.time.LocalTime.now());
        trackerRepository.save(tracker);
        trackerRepository.flush();
    }

    public void saveItem(Item item) {
        if (item.getName().matches("(?i)([a-z])* *(sausage)+ *(roll)+")) {
            item.setCalories(1000000);
        }
        itemRepository.save(item); // Assumes you have an ItemRepository
        itemRepository.flush();
    }

    public void deleteTrackerById(Integer trackerId) {
        trackerRepository.deleteById(trackerId); // Assumes you have a TrackerRepository
        trackerRepository.flush();
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Integer itemId) {
        return itemRepository.findById(itemId).isPresent() ? itemRepository.findById(itemId).get() : null;
    }

    public void deleteItemById(Integer itemId) {
        itemRepository.deleteById(itemId);
        itemRepository.flush();
    }
}
