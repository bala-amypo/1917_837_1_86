package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import java.util.List;
import java.util.stream.Collectors;

public class SuggestionServiceImpl implements SuggestionService {

    private final FarmService farmService;
    private final CatalogService catalogService;
    private final SuggestionRepository repo;

    public SuggestionServiceImpl(FarmService f, CatalogService c, SuggestionRepository r) {
        this.farmService = f;
        this.catalogService = c;
        this.repo = r;
    }

    @Override
    public Suggestion generateSuggestion(Long farmId) {

        Farm farm = farmService.getFarmById(farmId);

        List<Crop> crops = catalogService.findSuitableCrops(
                farm.getSoilPH(),
                farm.getWaterLevel(),
                farm.getSeason()
        );

        List<String> cropNames = crops.stream()
                .map(Crop::getName)
                .toList();

        List<Fertilizer> ferts = catalogService.findFertilizersForCrops(cropNames);

        Suggestion s = Suggestion.builder()
                .farm(farm)
                .suggestedCrops(String.join(",", cropNames))
                .suggestedFertilizers(
                        ferts.stream()
                                .map(Fertilizer::getName)
                                .collect(Collectors.joining(","))
                )
                .build();

        return repo.save(s);
    }

    @Override
    public Suggestion getSuggestion(Long id) {
        return repo.findById(id).orElse(null);
    }
}
