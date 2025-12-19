package com.example.demo.controller;

import com.example.demo.dto.CropRequest;
import com.example.demo.entity.Crop;
import com.example.demo.service.CropService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/crops")
public class CropController {

    private final CropService cropService;

    public CropController(CropService cropService) {
        this.cropService = cropService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Crop addCrop(@RequestBody CropRequest request) {
        return cropService.addCrop(request);
    }
}
