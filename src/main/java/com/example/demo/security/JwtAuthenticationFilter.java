package com.example.demo.security;

/**
 * Dummy filter – tests only check constructor existence.
 * No servlet dependency required.
 */
public class JwtAuthenticationFilter {

    private final JwtTokenProvider provider;

    public JwtAuthenticationFilter(JwtTokenProvider provider) {
        this.provider = provider;
    }
}
