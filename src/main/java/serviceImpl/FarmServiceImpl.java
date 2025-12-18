package com.example.demo.service.impl;

import com.example.demo.entity.Farm;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.FarmRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmServiceImpl implements FarmService {

    private final FarmRepository farmRepository;
    private final UserRepository userRepository;

    public Farm createFarm(Farm farm, Long ownerId) {
        farm.setOwner(userRepository.findById(ownerId).orElseThrow());
        return farmRepository.save(farm);
    }

    public List<Farm> getFarmsByOwner(Long ownerId) {
        return farmRepository.findByOwnerId(ownerId);
    }

    public Farm getFarmById(Long id) {
        return farmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Farm not found"));
    }
}
