package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Farm;
import com.example.demo.entity.Suggestion;

public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {

    List<Suggestion> findByFarm(Farm farm);
}
