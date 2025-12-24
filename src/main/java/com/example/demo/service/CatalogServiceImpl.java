package com.example.demo;

import com.example.demo.repository.*;

public class CatalogServiceImpl
        extends com.example.demo.service.impl.CatalogServiceImpl {

    public CatalogServiceImpl(CropRepository c, FertilizerRepository f) {
        super(c, f);
    }
}
