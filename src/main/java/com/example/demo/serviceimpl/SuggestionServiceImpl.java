package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Farm;
import com.example.demo.entity.Suggestion;
import com.example.demo.repository.SuggestionRepository;
import com.example.demo.service.CatalogService;
import com.example.demo.service.FarmService;
import com.example.demo.service.SuggestionService;

@Service
public class SuggestionServiceImpl implements SuggestionService {

    private final FarmService farmService;
    private final CatalogService catalogService;
    private final SuggestionRepository suggestionRepository;

    public SuggestionServiceImpl(FarmService farmService,
                                 CatalogService catalogService,
                                 SuggestionRepository suggestionRepository) {
        this.farmService = farmService;
        this.catalogService = catalogService;
        this.suggestionRepository = suggestionRepository;
    }

    @Override
    public Suggestion generateSuggestion(Long farmId) {

        Farm farm = farmService.getFarmById(farmId);

        var crops = catalogService.findSuitableCrops(
                farm.getSoilPH(),
                farm.getWaterLevel(),
                farm.getSeason()
        );

        List<String> cropNames = crops.stream()
                .map(c -> c.getName())
                .collect(Collectors.toList());

        var fertilizers = catalogService.findFertilizersForCrops(cropNames);

        String cropStr = String.join(",", cropNames);

        String fertStr = fertilizers.stream()
                .map(f -> f.getName())
                .collect(Collectors.joining(","));

        Suggestion suggestion = Suggestion.builder()
                .farm(farm)
                .suggestedCrops(cropStr)
                .suggestedFertilizers(fertStr)
                .build();

        return suggestionRepository.save(suggestion);
    }

    @Override
    public Suggestion getSuggestion(Long suggestionId) {
        return suggestionRepository.findById(suggestionId)
                .orElseThrow(() -> new RuntimeException("Suggestion not found"));
    }

    @Override
    public List<Suggestion> getSuggestionsByFarm(Long farmId) {
        return suggestionRepository.findByFarmId(farmId);
    }
}
