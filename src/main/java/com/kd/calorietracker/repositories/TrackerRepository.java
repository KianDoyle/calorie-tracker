package com.kd.calorietracker.repositories;

import com.kd.calorietracker.entities.Tracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackerRepository extends JpaRepository<Tracker, Integer> {
}
