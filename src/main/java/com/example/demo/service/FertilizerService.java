package com.example.demo.service;

import com.example.demo.dto.FertilizerRequest;
import com.example.demo.entity.Fertilizer;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.FertilizerRepository;
import org.springframework.stereotype.Service;

@Service
public class FertilizerService {

    private final FertilizerRepository fertilizerRepository;

    public FertilizerService(FertilizerRepository fertilizerRepository) {
        this.fertilizerRepository = fertilizerRepository;
    }

    public Fertilizer addFertilizer(FertilizerRequest request) {

        if (!request.getNpkRatio().matches("\\d+-\\d+-\\d+")) {
            throw new BadRequestException("NPK");
        }

        Fertilizer fertilizer = Fertilizer.builder()
                .name(request.getName())
                .npkRatio(request.getNpkRatio())
                .recommendedForCrops(request.getRecommendedForCrops())
                .build();

        return fertilizerRepository.save(fertilizer);
    }
}
