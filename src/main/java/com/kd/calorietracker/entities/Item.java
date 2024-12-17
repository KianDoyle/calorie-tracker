package com.kd.calorietracker.entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "items", schema = "tracker_db")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "calories", nullable = false)
    private Integer calories;

    @Column(name = "protein", nullable = false)
    private Float protein;

    @Column(name = "carbohydrates", nullable = false)
    private Float carbohydrates;

    @Column(name = "fats", nullable = false)
    private Float fats;

    @OneToMany(mappedBy = "item")
    private Set<Tracker> trackers = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Float getProtein() {
        return protein;
    }

    public void setProtein(Float protein) {
        this.protein = protein;
    }

    public Float getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Float carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Float getFats() {
        return fats;
    }

    public void setFats(Float fats) {
        this.fats = fats;
    }

    public Set<Tracker> getTrackers() {
        return trackers;
    }

    public void setTrackers(Set<Tracker> trackers) {
        this.trackers = trackers;
    }

}