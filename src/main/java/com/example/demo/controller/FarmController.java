package com.example.demo.controller;

import com.example.demo.dto.FarmRequest;
import com.example.demo.entity.Farm;
import com.example.demo.service.FarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/farms")
@RequiredArgsConstructor
public class FarmController {

    private final FarmService farmService;

    @PostMapping
    public Farm createFarm(@RequestBody FarmRequest req, Authentication auth) {
        Long userId = (Long) auth.getPrincipal();
        return farmService.createFarm(Farm.builder()
                .name(req.getName())
                .soilPH(req.getSoilPH())
                .waterLevel(req.getWaterLevel())
                .season(req.getSeason())
                .build(), userId);
    }

    @GetMapping
    public List<Farm> listFarms(Authentication auth) {
        return farmService.getFarmsByOwner((Long) auth.getPrincipal());
    }
}
