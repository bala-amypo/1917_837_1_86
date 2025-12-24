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

    public CatalogController(CatalogService service) {
        this.service = service;
    }

    private boolean isAdmin(Authentication auth) {
        for (GrantedAuthority a : auth.getAuthorities()) {
            if ("ROLE_ADMIN".equals(a.getAuthority())) return true;
        }
        return false;
    }

    @PostMapping("/crop")
    public ResponseEntity<?> addCrop(@RequestBody CropRequest r, Authentication auth) {

        if (!isAdmin(auth)) {
            return ResponseEntity.status(403).build();
        }

        Crop crop = new Crop();
        crop.setName(r.getName());
        crop.setSuitablePHMin(r.getPhMin());
        crop.setSuitablePHMax(r.getPhMax());
        crop.setRequiredWater(r.getWater());
        crop.setSeason(r.getSeason());

        return ResponseEntity.ok(service.addCrop(crop));
    }

    @PostMapping("/fertilizer")
    public ResponseEntity<?> addFertilizer(@RequestBody FertilizerRequest r, Authentication auth) {

        if (!isAdmin(auth)) {
            return ResponseEntity.status(403).build();
        }

        Fertilizer f = new Fertilizer();
        f.setName(r.getName());
        f.setNpkRatio(r.getNpkRatio());
        f.setRecommendedForCrops(r.getRecommendedForCrops());

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
