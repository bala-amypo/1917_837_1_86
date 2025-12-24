package com.example.demo;

public class SuggestionServiceImpl
        extends com.example.demo.service.impl.SuggestionServiceImpl {

    public SuggestionServiceImpl(
            com.example.demo.service.FarmService f,
            com.example.demo.service.CatalogService c,
            com.example.demo.repository.SuggestionRepository r) {
        super(f, c, r);
    }
}
