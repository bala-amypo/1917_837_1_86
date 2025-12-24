package com.example.demo;

public class FarmServiceImpl
        extends com.example.demo.service.impl.FarmServiceImpl {

    public FarmServiceImpl(
            FarmRepository f,
            UserRepository u) {
        super(f, u);
    }
}
