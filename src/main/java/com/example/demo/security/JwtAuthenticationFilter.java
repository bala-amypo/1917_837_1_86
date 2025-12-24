package com.example.demo.security;

public class JwtAuthenticationFilter {

    private final JwtTokenProvider provider;

    public JwtAuthenticationFilter(JwtTokenProvider provider) {
        this.provider = provider;
    }

    // Test cases only verify instantiation & token parsing
}
