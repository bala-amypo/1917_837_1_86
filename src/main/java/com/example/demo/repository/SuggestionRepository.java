package com.example.demo.repository;

import com.example.demo.entity.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {

    @Query("from Suggestion s where s.farm.id=:farmId")
    List<Suggestion> findByFarmId(Long farmId);
}
