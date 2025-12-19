package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Crop;

public interface CropRepository extends JpaRepository<Crop, Long> {

    List<Crop> findBySeason(String season);
}
