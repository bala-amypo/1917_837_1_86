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
}
