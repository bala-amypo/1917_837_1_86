package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
