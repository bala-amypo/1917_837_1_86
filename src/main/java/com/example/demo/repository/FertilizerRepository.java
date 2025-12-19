package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Fertilizer;

public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {
}
