package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.service.CatalogService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    private final CatalogService service;

    public CatalogController(CatalogService s) {
        this.service = s;
    }

    private boolean isAdmin(Authentication auth) {
        for (GrantedAuthority a : auth.getAuthorities()) {
            if (a.getAuthority().equals("ROLE_ADMIN")) return true;
        }
        return false;
    }

    @PostMapping("/crop")
    public ResponseEntity<?> addCrop(@RequestBody CropRequest r, Authentication auth) {

        if (!isAdmin(auth)) {
            return ResponseEntity.status(403).build();
        }

        Crop c = Crop.builder()
                .name(r.getName())
                .suitablePHMin(r.getPhMin())
                .suitablePHMax(r.getPhMax())
                .requiredWater(r.getWater())
                .season(r.getSeason())
                .build();

        return ResponseEntity.ok(service.addCrop(c));
    }

    @PostMapping("/fertilizer")
    public ResponseEntity<?> addFertilizer(@RequestBody FertilizerRequest r, Authentication auth) {

        if (!isAdmin(auth)) {
            return ResponseEntity.status(403).build();
        }

        Fertilizer f = Fertilizer.builder()
                .name(r.getName())
                .npkRatio(r.getNpkRatio())
                .recommendedForCrops(r.getRecommendedForCrops())
                .build();

        return ResponseEntity.ok(service.addFertilizer(f));
    }

    @GetMapping("/crops")
    public ResponseEntity<List<Crop>> findCrops(
            double ph,
            double water,
            String season) {

        return ResponseEntity.ok(service.findSuitableCrops(ph, water, season));
    }

    @GetMapping("/fertilizers")
    public ResponseEntity<List<Fertilizer>> findFerts(String crop) {

        return ResponseEntity.ok(service.findFertilizersForCrops(List.of(crop)));
    }
}
