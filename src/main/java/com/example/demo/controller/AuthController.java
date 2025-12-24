package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwt;

    public AuthController(UserService u, JwtTokenProvider j) {
        this.userService = u;
        this.jwt = j;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest r) {

        User user = User.builder()
                .name(r.getName())
                .email(r.getEmail())
                .password(r.getPassword())
                .role("USER")
                .build();

        return ResponseEntity.ok(userService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest r) {

        User user = userService.findByEmail(r.getEmail());
        if (user == null) {
            return ResponseEntity.status(401).build();
        }

        if (!new BCryptPasswordEncoder().matches(r.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).build();
        }

        String token = jwt.createToken(user.getId(), user.getEmail(), user.getRole());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
