package com.example.demo.repository;

import com.example.demo.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CropRepository extends JpaRepository<Crop, Long> {

    @Query("select c from Crop c where :ph between c.suitablePHMin and c.suitablePHMax and c.requiredWater <= :water and c.season = :season")
    List<Crop> findSuitableCrops(Double ph, Double water, String season);
}
    