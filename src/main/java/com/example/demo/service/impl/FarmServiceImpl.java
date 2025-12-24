package com.example.demo.service.impl;

import com.example.demo.entity.Farm;
import com.example.demo.repository.FarmRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FarmService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmServiceImpl implements FarmService {

    private final FarmRepository farmRepo;
    private final UserRepository userRepo;

    public FarmServiceImpl(FarmRepository f, UserRepository u) {
        this.farmRepo = f;
        this.userRepo = u;
    }

    @Override
    public Farm createFarm(Farm farm, Long ownerId) {

        if (farm.getSoilPH() < 3 || farm.getSoilPH() > 10) {
            throw new IllegalArgumentException("Invalid pH");
        }

        farm.setOwner(userRepo.findById(ownerId).orElse(null));
        return farmRepo.save(farm);
    }

    @Override
    public Farm getFarmById(Long id) {
        return farmRepo.findById(id).orElse(null);
    }

    @Override
    public List<Farm> getFarmsByOwner(Long ownerId) {
        return farmRepo.findByOwnerId(ownerId);
    }
}
