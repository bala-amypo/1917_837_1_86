package com.example.demo.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.*;
import com.example.demo.entity.Crop;
import com.example.demo.entity.Fertilizer;
import com.example.demo.service.CatalogService;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    private void checkAdmin(Authentication auth) {
        if (auth.getAuthorities().stream()
                .noneMatch(a -> a.getAuthority().equals("ADMIN"))) {
            throw new RuntimeException("Access denied");
        }
    }

    @PostMapping("/crop")
    public Crop addCrop(@RequestBody CropRequest req,
                        Authentication auth) {

        checkAdmin(auth);

        Crop crop = Crop.builder()
                .name(req.getName())
                .suitablePHMin(req.getSuitablePHMin())
                .suitablePHMax(req.getSuitablePHMax())
                .requiredWater(req.getRequiredWater())
                .season(req.getSeason())
                .build();

        return catalogService.addCrop(crop);
    }

    @PostMapping("/fertilizer")
    public Fertilizer addFertilizer(@RequestBody FertilizerRequest req,
                                    Authentication auth) {

        checkAdmin(auth);

        Fertilizer fertilizer = Fertilizer.builder()
                .name(req.getName())
                .npkRatio(req.getNpkRatio())
                .recommendedForCrops(req.getRecommendedForCrops())
                .build();

        return catalogService.addFertilizer(fertilizer);
    }

    @GetMapping("/crops/suitable")
    public List<Crop> suitableCrops(@RequestParam Double ph,
                                    @RequestParam Double water,
                                    @RequestParam String season) {
        return catalogService.findSuitableCrops(ph, water, season);
    }

    @GetMapping("/fertilizers/by-crop")
    public List<Fertilizer> fertilizers(@RequestParam String name) {
        return catalogService.findFertilizersForCrops(List.of(name));
    }
}
