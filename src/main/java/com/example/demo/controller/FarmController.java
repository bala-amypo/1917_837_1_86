package com.example.demo.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.FarmRequest;
import com.example.demo.entity.Farm;
import com.example.demo.service.FarmService;

@RestController
@RequestMapping("/farms")
public class FarmController {

    private final FarmService farmService;

    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }

    @PostMapping
    public Farm createFarm(@RequestBody FarmRequest req,
                           Authentication auth) {

        Long ownerId = (Long) auth.getPrincipal();

        Farm farm = Farm.builder()
                .name(req.getName())
                .soilPH(req.getSoilPH())
                .waterLevel(req.getWaterLevel())
                .season(req.getSeason())
                .build();

        return farmService.createFarm(farm, ownerId);
    }

    @GetMapping
    public List<Farm> listFarms(Authentication auth) {
        Long ownerId = (Long) auth.getPrincipal();
        return farmService.getFarmsByOwner(ownerId);
    }

    @GetMapping("/{farmId}")
    public Farm getFarm(@PathVariable Long farmId) {
        return farmService.getFarmById(farmId);
    }
}
