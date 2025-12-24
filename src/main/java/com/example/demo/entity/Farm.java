package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Farm {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private double soilPH;
    private double waterLevel;
    private String season;

    @ManyToOne
    private User owner;

    public Farm() {}

    public Long getId() { return id; }
    public double getSoilPH() { return soilPH; }
    public double getWaterLevel() { return waterLevel; }
    public String getSeason() { return season; }

    public void setOwner(User u) { this.owner = u; }
}
