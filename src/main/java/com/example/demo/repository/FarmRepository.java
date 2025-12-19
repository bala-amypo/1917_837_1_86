package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Farm;
import com.example.demo.entity.User;

public interface FarmRepository extends JpaRepository<Farm, Long> {

    List<Farm> findByOwner(User owner);
}
