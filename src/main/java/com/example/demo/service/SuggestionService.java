package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuggestionService {

    private final FarmRepository farmRepository;
    private final CropRepository cropRepository;
    private final FertilizerRepository fertilizerRepository;
    private final SuggestionRepository suggestionRepository;

    public SuggestionService(FarmRepository farmRepository,
                             CropRepository cropRepository,
                             FertilizerRepository fertilizerRepository,
                             SuggestionRepository suggestionRepository) {
        this.farmRepository = farmRepository;
        this.cropRepository = cropRepository;
        this.fertilizerRepository = fertilizerRepository;
        this.suggestionRepository = suggestionRepository;
    }

    public Suggestion generateSuggestion(Long farmId) {

        Farm farm = farmRepository.findById(farmId)
                .orElseThrow(() -> new ResourceNotFoundException("Farm not found"));

        List<Crop> crops = cropRepository.findBySeason(farm.getSeason())
                .stream()
                .filter(c ->
                        farm.getSoilPH() >= c.getSuitablePHMin()
                        && farm.getSoilPH() <= c.getSuitablePHMax())
                .toList();

        String cropNames = crops.stream()
                .map(Crop::getName)
                .collect(Collectors.joining(","));

        String fertilizerNames = fertilizerRepository.findAll().stream()
                .map(Fertilizer::getName)
                .collect(Collectors.joining(","));

        Suggestion suggestion = Suggestion.builder()
                .farm(farm)
                .suggestedCrops(cropNames)
                .suggestedFertilizers(fertilizerNames)
                .build();

        return suggestionRepository.save(suggestion);
    }
}
