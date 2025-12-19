package com.example.demo.controller;

import com.example.demo.dto.FertilizerRequest;
import com.example.demo.entity.Fertilizer;
import com.example.demo.service.FertilizerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {

    private final FertilizerService fertilizerService;

    public FertilizerController(FertilizerService fertilizerService) {
        this.fertilizerService = fertilizerService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Fertilizer addFertilizer(@RequestBody FertilizerRequest request) {
        return fertilizerService.addFertilizer(request);
    }
}
