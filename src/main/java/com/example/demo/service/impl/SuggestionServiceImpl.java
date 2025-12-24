package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.SuggestionRepository;
import com.example.demo.service.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuggestionServiceImpl implements SuggestionService {

    private final SuggestionRepository repo;
    private final FarmService farmService;

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
