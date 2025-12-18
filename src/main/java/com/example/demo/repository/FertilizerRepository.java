package com.example.demo.repository;

import com.example.demo.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {
    List<Fertilizer> findByRecommendedForCropsContaining(String cropName);
}
