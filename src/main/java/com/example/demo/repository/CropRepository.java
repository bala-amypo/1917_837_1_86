package com.example.demo.repository;

import com.example.demo.entity.Crop;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CropRepository extends JpaRepository<Crop, Long> {

    @Query("from Crop c where :ph between c.suitablePHMin and c.suitablePHMax and c.season=:season")
    List<Crop> findSuitableCrops(double ph, String season);
}
