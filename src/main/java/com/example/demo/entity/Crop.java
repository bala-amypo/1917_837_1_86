package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Crop {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private double suitablePHMin;
    private double suitablePHMax;
    private String season;
    private double requiredWater;

    public Crop() {}

    public String getName() { return name; }
    public double getSuitablePHMin() { return suitablePHMin; }
    public double getSuitablePHMax() { return suitablePHMax; }
    public String getSeason() { return season; }

    public void setName(String name) { this.name = name; }
    public void setSuitablePHMin(double suitablePHMin) { this.suitablePHMin = suitablePHMin; }
    public void setSuitablePHMax(double suitablePHMax) { this.suitablePHMax = suitablePHMax; }
    public void setSeason(String season) { this.season = season; }
    public void setRequiredWater(double requiredWater) { this.requiredWater = requiredWater; }
}
