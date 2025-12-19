package com.example.demo.controller;

import com.example.demo.dto.FarmRequest;
import com.example.demo.entity.Farm;
import com.example.demo.service.FarmService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/farms")
public class FarmController {

    private final FarmService farmService;

    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }

    @PostMapping
    public Farm createFarm(@RequestBody FarmRequest request,
                           Authentication authentication) {
        return farmService.createFarm(request, authentication.getName());
    }

    @GetMapping
    public List<Farm> getMyFarms(Authentication authentication) {
        return farmService.getMyFarms(authentication.getName());
    }
}
