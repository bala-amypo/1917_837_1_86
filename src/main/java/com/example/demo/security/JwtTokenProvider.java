package com.example.demo.security;

import java.util.Base64;

public class JwtTokenProvider {

    public String createToken(Long userId, String email, String role) {
        String raw = userId + "|" + email + "|" + role;
        return Base64.getEncoder().encodeToString(raw.getBytes());
    }

    private String[] parse(String token) {
        return new String(Base64.getDecoder().decode(token)).split("\\|");
    }

    public boolean validateToken(String token) {
        return parse(token).length == 3;
    }

    public Long getUserId(String token) {
        return Long.valueOf(parse(token)[0]);
    }

    public String getEmail(String token) {
        return parse(token)[1];
    }

    public String getRole(String token) {
        return parse(token)[2];
    }
}
