package com.example.demo;

import com.example.demo.repository.SuggestionRepository;
import com.example.demo.service.*;

public class SuggestionServiceImpl
        extends com.example.demo.service.impl.SuggestionServiceImpl {

    public SuggestionServiceImpl(FarmService fs, CatalogService cs, SuggestionRepository r) {
        super(fs, cs, r);
    }
}
