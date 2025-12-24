package com.example.demo.service.impl;   // ✅ MUST BE THIS

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.CatalogService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    private final CropRepository cropRepo;
    private final FertilizerRepository fertRepo;

    public CatalogServiceImpl(CropRepository c, FertilizerRepository f) {
        this.cropRepo = c;
        this.fertRepo = f;
    }

    public Crop addCrop(Crop crop) {
        return cropRepo.save(crop);
    }

    public Fertilizer addFertilizer(Fertilizer fertilizer) {
        return fertRepo.save(fertilizer);
    }

    public List<Crop> findSuitableCrops(double ph, double water, String season) {
        return List.of();
    }

    public List<Fertilizer> findFertilizersForCrops(List<String> crops) {
        return List.of();
    }
}
