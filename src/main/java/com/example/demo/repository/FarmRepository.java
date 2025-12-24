package com.example.demo.repository;

import com.example.demo.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FarmRepository extends JpaRepository<Farm, Long> {

    // List all farms owned by a user
    List<Farm> findByOwnerId(Long ownerId);

    // Ownership validation (future-safe, test-friendly)
    Optional<Farm> findByIdAndOwnerId(Long farmId, Long ownerId);
}
