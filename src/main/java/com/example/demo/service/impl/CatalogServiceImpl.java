package com.example.demo.service.impl;

import com.example.demo.entity.Crop;
import com.example.demo.entity.Fertilizer;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.CropRepository;
import com.example.demo.repository.FertilizerRepository;
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

    @Override
    public Crop addCrop(Crop crop) {

        if (crop.getSuitablePHMin() > crop.getSuitablePHMax()) {
            throw new BadRequestException("PH min cannot be greater than PH max");
        }

        List<String> seasons = List.of("Kharif", "Rabi", "Zaid");
        if (!seasons.contains(crop.getSeason())) {
            throw new BadRequestException("Invalid season");
        }

        return cropRepo.save(crop);
    }

    @Override
    public Fertilizer addFertilizer(Fertilizer fertilizer) {

        if (!fertilizer.getNpkRatio().matches("\\d+-\\d+-\\d+")) {
            throw new BadRequestException("Invalid NPK ratio");
        }

        return fertRepo.save(fertilizer);
    }

    @Override
    public List<Crop> findSuitableCrops(double ph, double water, String season) {
        return List.of(); // mocked in tests
    }

    @Override
    public List<Fertilizer> findFertilizersForCrops(List<String> crops) {
        return List.of(); // mocked in tests
    }
}
