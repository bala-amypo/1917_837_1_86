package com.example.demo.service.impl;

import com.example.demo.entity.Crop;
import com.example.demo.entity.Farm;
import com.example.demo.entity.Fertilizer;
import com.example.demo.entity.Suggestion;
import com.example.demo.repository.SuggestionRepository;
import com.example.demo.service.CatalogService;
import com.example.demo.service.FarmService;
import com.example.demo.service.SuggestionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuggestionServiceImpl implements SuggestionService {

    private final FarmService farmService;
    private final CatalogService catalogService;
    private final SuggestionRepository repo;

    public SuggestionServiceImpl(
            FarmService fs,
            CatalogService cs,
            SuggestionRepository r) {
        this.farmService = fs;
        this.catalogService = cs;
        this.repo = r;
    }

    @Override
    public Suggestion generateSuggestion(Long farmId) {

        Farm farm = farmService.getFarmById(farmId);

        List<Crop> crops =
                catalogService.findSuitableCrops(
                        farm.getSoilPH(),
                        farm.getWaterLevel(),
                        farm.getSeason());

        List<String> cropNames =
                crops.stream().map(Crop::getName).toList();

        List<Fertilizer> ferts =
                catalogService.findFertilizersForCrops(cropNames);

        Suggestion s = new Suggestion();
        s.setFarm(farm);
        s.setSuggestedCrops(String.join(",", cropNames));
        s.setSuggestedFertilizers(
                ferts.stream().map(Fertilizer::getName).toList().toString()
        );

        return repo.save(s);
    }

    @Override
    public Suggestion getSuggestion(Long id) {
        return repo.findById(id).orElse(null);
    }
}
