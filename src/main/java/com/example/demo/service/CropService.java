package com.example.demo.service;

import com.example.demo.dto.CropRequest;
import com.example.demo.entity.Crop;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.CropRepository;
import com.example.demo.util.ValidationUtil;
import org.springframework.stereotype.Service;

@Service
public class CropService {

    private final CropRepository cropRepository;

    public CropService(CropRepository cropRepository) {
        this.cropRepository = cropRepository;
    }

    public Crop addCrop(CropRequest request) {

        if (request.getSuitablePHMin() >= request.getSuitablePHMax()) {
            throw new BadRequestException("PH min");
        }

        if (!ValidationUtil.validSeason(request.getSeason())) {
            throw new BadRequestException("Invalid season");
        }

        Crop crop = Crop.builder()
                .name(request.getName())
                .suitablePHMin(request.getSuitablePHMin())
                .suitablePHMax(request.getSuitablePHMax())
                .requiredWater(request.getRequiredWater())
                .season(request.getSeason())
                .build();

        return cropRepository.save(crop);
    }
}
