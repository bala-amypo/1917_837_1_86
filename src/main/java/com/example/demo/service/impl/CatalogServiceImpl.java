package com.example.demo.service.impl;

import com.example.demo.entity.Crop;
import com.example.demo.entity.Fertilizer;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.CropRepository;
import com.example.demo.repository.FertilizerRepository;
import com.example.demo.service.CatalogService;
import com.example.demo.util.ValidationUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class CatalogServiceImpl implements CatalogService {

    private final CropRepository cropRepository;
    private final FertilizerRepository fertilizerRepository;

    private static final Pattern NPK_PATTERN =
            Pattern.compile("\\d+-\\d+-\\d+");

    public CatalogServiceImpl(CropRepository cropRepository,
                              FertilizerRepository fertilizerRepository) {
        this.cropRepository = cropRepository;
        this.fertilizerRepository = fertilizerRepository;
    }

    @Override
    public Crop addCrop(Crop crop) {

        if (crop.getSuitablePHMin() > crop.getSuitablePHMax()) {
            throw new BadRequestException("PH min must be <= PH max");
        }

        if (!ValidationUtil.validSeason(crop.getSeason())) {
            throw new BadRequestException("Invalid season");
        }

        return cropRepository.save(crop);
    }

    @Override
    public Fertilizer addFertilizer(Fertilizer fertilizer) {

        if (!NPK_PATTERN.matcher(fertilizer.getNpkRatio()).matches()) {
            throw new BadRequestException("Invalid NPK format");
        }

        return fertilizerRepository.save(fertilizer);
    }

    @Override
    public List<Crop> findSuitableCrops(Double ph,
                                        Double waterLevel,
                                        String season) {
        return cropRepository.findSuitableCrops(ph, season);
    }

    @Override
    public List<Fertilizer> findFertilizersForCrops(List<String> cropNames) {

        List<Fertilizer> result = new ArrayList<>();

        for (String crop : cropNames) {
            result.addAll(fertilizerRepository.findByCropName(crop));
        }

        return result;
    }
}
