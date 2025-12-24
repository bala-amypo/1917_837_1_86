package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.SuggestionRepository;
import com.example.demo.service.*;

import java.util.List;
import java.util.stream.Collectors;

public class SuggestionServiceImpl implements SuggestionService {

    private final FarmService farmService;
    private final CatalogService catalogService;
    private final SuggestionRepository repo;

    public SuggestionServiceImpl(
            FarmService farmService,
            CatalogService catalogService,
            SuggestionRepository repo) {

        this.farmService = farmService;
        this.catalogService = catalogService;
        this.repo = repo;
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
                .collect(Collectors.toList());

        List<Fertilizer> fertilizers =
                catalogService.findFertilizersForCrops(cropNames);

        Suggestion s = new Suggestion();
        s.setFarm(farm);
        s.setSuggestedCrops(String.join(",", cropNames));
        s.setSuggestedFertilizers(
                fertilizers.stream()
                        .map(Fertilizer::getName)
                        .collect(Collectors.joining(","))
        );

        return repo.save(s);
    }

    @Override
    public Suggestion getSuggestion(Long id) {
        return repo.findById(id).orElse(null);
    }
}
