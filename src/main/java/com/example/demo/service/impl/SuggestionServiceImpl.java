package com.example.demo;

public class SuggestionServiceImpl
        extends com.example.demo.service.impl.SuggestionServiceImpl {

    public SuggestionServiceImpl(
            FarmService fs,
            CatalogService cs,
            SuggestionRepository r) {
        super(fs, cs, r);
    }
}
