package com.example.demo.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.FarmRequest;
import com.example.demo.entity.Farm;
import com.example.demo.entity.User;
import com.example.demo.service.FarmService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/farms")
public class FarmController {

    private final FarmService farmService;
    private final UserService userService;

    public FarmController(FarmService farmService,
                          UserService userService) {
        this.farmService = farmService;
        this.userService = userService;
    }

    @PostMapping
    public Farm createFarm(@RequestBody FarmRequest request,
                           Authentication authentication) {

        User user = userService.findByEmail(authentication.getName());

        Farm farm = Farm.builder()
                .name(request.getName())
                .soilPH(request.getSoilPH())
                .waterLevel(request.getWaterLevel())
                .season(request.getSeason())
                .build();

        return farmService.createFarm(farm, user.getId());
    }

    @GetMapping
    public List<Farm> listFarms(Authentication authentication) {

        User user = userService.findByEmail(authentication.getName());
        return farmService.getFarmsByOwner(user.getId());
    }

    @GetMapping("/{farmId}")
    public Farm getFarm(@PathVariable Long farmId) {
        return farmService.getFarmById(farmId);
    }
}
