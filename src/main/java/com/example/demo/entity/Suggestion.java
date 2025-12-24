package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Suggestion {

    @Id
    @GeneratedValue
    private Long id;
    private String suggestedCrops;
    private String suggestedFertilizers;

    @ManyToOne
    private Farm farm;

    public Suggestion() {}

    public Long getId() { return id; }
    public String getSuggestedCrops() { return suggestedCrops; }

    public void setFarm(Farm f) { this.farm = f; }
    public void setSuggestedCrops(String s) { this.suggestedCrops = s; }
    public void setSuggestedFertilizers(String s) { this.suggestedFertilizers = s; }
}
