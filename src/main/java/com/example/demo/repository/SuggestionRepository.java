package com.example.demo.repository;

import com.example.demo.entity.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {

    // Fetch suggestions for a specific farm
    List<Suggestion> findByFarmId(Long farmId);
}
