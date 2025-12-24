package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.FarmService;

import java.util.List;

public class FarmServiceImpl implements FarmService {

    private final FarmRepository farmRepo;
    private final UserRepository userRepo;

    public FarmServiceImpl(FarmRepository f, UserRepository u) {
        this.farmRepo = f;
        this.userRepo = u;
    }

    @Override
    public Farm createFarm(Farm farm, Long ownerId) {

        User owner = userRepo.findById(ownerId)
                .orElseThrow(() -> new IllegalArgumentException("Owner not found"));

        if (farm.getSoilPH() < 3.0 || farm.getSoilPH() > 10.0) {
            throw new IllegalArgumentException("Invalid pH value");
        }

        farm.setOwner(owner);
        return farmRepo.save(farm);
    }

    @Override
    public Farm getFarmById(Long id) {
        return farmRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Farm not found"));
    }

    @Override
    public List<Farm> getFarmsByOwner(Long ownerId) {
        return farmRepo.findByOwnerId(ownerId);
    }
}
