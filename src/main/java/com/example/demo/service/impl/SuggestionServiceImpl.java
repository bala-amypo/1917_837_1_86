package com.example.demo.service.impl;

import com.example.demo.entity.Suggestion;
import com.example.demo.repository.SuggestionRepository;
import com.example.demo.service.CatalogService;
import com.example.demo.service.FarmService;
import com.example.demo.service.SuggestionService;
import org.springframework.stereotype.Service;

@Service
public class SuggestionServiceImpl implements SuggestionService {

    private final FarmService farmService;
    private final SuggestionRepository repo;

    public SuggestionServiceImpl(
            FarmService farmService,
            CatalogService catalogService,
            SuggestionRepository repo) {
        this.farmService = farmService;
        this.repo = repo;
    }

    public Suggestion generateSuggestion(Long farmId) {
        Suggestion s = new Suggestion();
        s.setFarm(farmService.getFarmById(farmId));
        return repo.save(s);
    }

    public Suggestion getSuggestion(Long id) {
        return repo.findById(id).orElse(null);
    }
}
