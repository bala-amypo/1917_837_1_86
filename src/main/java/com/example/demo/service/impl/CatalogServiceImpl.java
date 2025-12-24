package com.example.demo;

public class CatalogServiceImpl
        extends com.example.demo.service.impl.CatalogServiceImpl {

    public CatalogServiceImpl(
            com.example.demo.repository.CropRepository c,
            com.example.demo.repository.FertilizerRepository f) {
        super(c, f);
    }
}
