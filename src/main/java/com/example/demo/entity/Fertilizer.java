package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Fertilizer {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String npkRatio;
    private String recommendedForCrops;

    public Fertilizer() {}

    public String getName() { return name; }
    public String getNpkRatio() { return npkRatio; }

    public void setName(String name) { this.name = name; }
    public void setNpkRatio(String npkRatio) { this.npkRatio = npkRatio; }
    public void setRecommendedForCrops(String recommendedForCrops) {
        this.recommendedForCrops = recommendedForCrops;
    }
}
