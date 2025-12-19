package com.example.demo.controller;

import com.example.demo.dto.FarmRequest;
import com.example.demo.entity.Farm;
import com.example.demo.service.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/farms")
public class FarmController {

    @Autowired
    private FarmService farmService;

    @PostMapping
    public Farm createFarm(@RequestBody FarmRequest req, Authentication auth) {

        Farm farm = new Farm();
        farm.setName(req.getName());
        farm.setSoilPH(req.getSoilPH());
        farm.setWaterLevel(req.getWaterLevel());
        farm.setSeason(req.getSeason());

        Long userId = (Long) auth.getPrincipal();
        return farmService.createFarm(farm, userId);
    }

    @GetMapping
    public List<Farm> getFarms(Authentication auth) {
        return farmService.getFarmsByOwner((Long) auth.getPrincipal());
    }
}
