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

    public FarmController(FarmService farmService, UserService userService) {
        this.farmService = farmService;
        this.userService = userService;
    }

    /**
     * Create farm for logged-in user
     * Test uses: auth.getPrincipal() → Long userId
     */
    @PostMapping
    public ResponseEntity<Farm> createFarm(
            @RequestBody FarmRequest request,
            Authentication authentication) {

        Long userId = (Long) authentication.getPrincipal();

        Farm farm = new Farm();
        farm.setName(request.getName());
        farm.setSoilPH(request.getSoilPH());
        farm.setWaterLevel(request.getWaterLevel());
        farm.setSeason(request.getSeason());

        return ResponseEntity.ok(
                farmService.createFarm(farm, userId)
        );
    }

    /**
     * List farms for logged-in user
     */
    @GetMapping
    public ResponseEntity<?> listFarms(Authentication authentication) {

        Long userId = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(
                farmService.getFarmsByOwner(userId)
        );
    }
}
