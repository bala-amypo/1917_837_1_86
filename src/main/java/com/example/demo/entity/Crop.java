package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Crop {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private double suitablePHMin;
    private double suitablePHMax;
    private String season;
    private double requiredWater;
}
