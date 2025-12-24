package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Suggestion {

    @Id
    @GeneratedValue
    private Long id;

    private String suggestedCrops;
    private String suggestedFertilizers;

    private LocalDateTime createdAt;

    @ManyToOne
    private Farm farm;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
