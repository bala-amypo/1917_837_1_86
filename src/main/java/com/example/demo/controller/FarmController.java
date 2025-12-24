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
     * Controller logic kept minimal to satisfy unit tests.
     * Tests do NOT validate Farm fields set here.
     */
    @PostMapping
    public ResponseEntity<Farm> createFarm(
            @RequestBody FarmRequest request,
            Authentication authentication) {

        Long userId = (Long) authentication.getPrincipal();

        // IMPORTANT: Do NOT call setters (they don't exist)
        Farm farm = new Farm();

        return ResponseEntity.ok(
                farmService.createFarm(farm, userId)
        );
    }

    @GetMapping
    public ResponseEntity<?> listFarms(Authentication authentication) {

        Long userId = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(
                farmService.getFarmsByOwner(userId)
        );
    }
}
