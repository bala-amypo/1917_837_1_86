package com.example.demo;

public class FarmServiceImpl
        extends com.example.demo.service.impl.FarmServiceImpl {

    public FarmServiceImpl(
            com.example.demo.repository.FarmRepository f,
            com.example.demo.repository.UserRepository u) {
        super(f, u);
    }
}
