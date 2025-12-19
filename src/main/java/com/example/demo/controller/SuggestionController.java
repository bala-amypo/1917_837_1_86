package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Suggestion;
import com.example.demo.service.SuggestionService;

@RestController
@RequestMapping("/suggestions")
public class SuggestionController {

    private final SuggestionService suggestionService;

    public SuggestionController(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    @PostMapping("/{farmId}")
    public Suggestion generate(@PathVariable Long farmId) {
        return suggestionService.generateSuggestion(farmId);
    }

    @GetMapping("/{suggestionId}")
    public Suggestion get(@PathVariable Long suggestionId) {
        return suggestionService.getSuggestion(suggestionId);
    }

    @GetMapping("/farm/{farmId}")
    public List<Suggestion> list(@PathVariable Long farmId) {
        return suggestionService.getSuggestionsByFarm(farmId);
    }
}
