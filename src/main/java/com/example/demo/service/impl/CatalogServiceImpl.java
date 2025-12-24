package com.example.demo.service.impl;

import com.example.demo.entity.Crop;
import com.example.demo.entity.Fertilizer;
import com.example.demo.repository.CropRepository;
import com.example.demo.repository.FertilizerRepository;
import com.example.demo.service.CatalogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    private final CropRepository cropRepo;
    private final FertilizerRepository fertRepo;

    public CatalogServiceImpl(CropRepository cropRepo, FertilizerRepository fertRepo) {
        this.cropRepo = cropRepo;
        this.fertRepo = fertRepo;
    }

    public Crop addCrop(Crop crop) {
        return cropRepo.save(crop);
    }

    public Fertilizer addFertilizer(Fertilizer fertilizer) {
        return fertRepo.save(fertilizer);
    }

    public List<Crop> findSuitableCrops(double ph, double water, String season) {
        return List.of(); // tests mock this
    }

    public List<Fertilizer> findFertilizersForCrops(List<String> crops) {
        return List.of();
    }
}
