package com.example.demo.dto;

public class AuthRequest {
    private String email;
    private String password;

    public AuthRequest(String e, String p) {
        email = e;
        password = p;
    }

    public String getEmail() { return email; }
    public String getPassword() { return password; }
}
