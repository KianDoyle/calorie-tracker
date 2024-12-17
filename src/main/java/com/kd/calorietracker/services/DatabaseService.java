package com.kd.calorietracker.services;

import com.kd.calorietracker.entities.Tracker;
import com.kd.calorietracker.repositories.TrackerRepository;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    private final TrackerRepository trackerRepository;

    public DatabaseService(TrackerRepository trackerRepository) {
        this.trackerRepository = trackerRepository;
    }

    public Tracker getTracker() {
        return trackerRepository.findAll().getFirst();
    }
}
