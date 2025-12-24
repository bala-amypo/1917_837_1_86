package com.example.demo.controller;

import com.example.demo.dto.FarmRequest;
import com.example.demo.entity.Farm;
import com.example.demo.service.FarmService;
import com.example.demo.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/farms")
public class FarmController {

    private final FarmService farmService;
    private final UserService userService;

    public FarmController(FarmService f, UserService u) {
        this.farmService = f;
        this.userService = u;
    }

    @PostMapping
    public ResponseEntity<Farm> createFarm(
            @RequestBody FarmRequest r,
            Authentication auth) {

        Long userId = (Long) auth.getPrincipal();

        Farm farm = Farm.builder()
                .name(r.getName())
                .soilPH(r.getSoilPH())
                .waterLevel(r.getWaterLevel())
                .season(r.getSeason())
                .build();

        return ResponseEntity.ok(farmService.createFarm(farm, userId));
    }

    @GetMapping
    public ResponseEntity<?> listFarms(Authentication auth) {

        Long userId = (Long) auth.getPrincipal();
        return ResponseEntity.ok(farmService.getFarmsByOwner(userId));
    }
}
