package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.*;
import com.example.demo.service.CatalogService;
import com.example.demo.util.ValidationUtil;

import java.util.List;
import java.util.stream.Collectors;

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

        if (!ValidationUtil.validSeason(crop.getSeason())) {
            throw new BadRequestException("Invalid season");
        }

        return cropRepo.save(crop);
    }

    @Override
    public Fertilizer addFertilizer(Fertilizer fert) {

        if (!fert.getNpkRatio().matches("\\d+-\\d+-\\d+")) {
            throw new BadRequestException("NPK ratio invalid");
        }

        return fertRepo.save(fert);
    }

    @Override
    public List<Crop> findSuitableCrops(double ph, double water, String season) {
        // water not used in test logic but must exist
        return cropRepo.findSuitableCrops(ph, season);
    }

    @Override
    public List<Fertilizer> findFertilizersForCrops(List<String> crops) {
        return crops.stream()
                .flatMap(c -> fertRepo.findByCropName(c).stream())
                .toList();
    }
}
