package com.example.demo.service.impl;

import com.example.demo.entity.Farm;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.FarmRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FarmService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmServiceImpl implements FarmService {

    private final FarmRepository farmRepo;
    private final UserRepository userRepo;

    public FarmServiceImpl(FarmRepository farmRepo, UserRepository userRepo) {
        this.farmRepo = farmRepo;
        this.userRepo = userRepo;
    }

    public Farm createFarm(Farm farm, Long ownerId) {
        farm.setOwner(userRepo.findById(ownerId).orElse(null));
        return farmRepo.save(farm);
    }

    public Farm getFarmById(Long id) {
        return farmRepo.findById(id).orElse(null);
    }

    public List<Farm> getFarmsByOwner(Long ownerId) {
        return farmRepo.findByOwnerId(ownerId);
    }
}
