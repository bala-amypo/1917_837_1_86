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

    public AuthController(UserService userService, JwtTokenProvider jwt) {
        this.userService = userService;
        this.jwt = jwt;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest r) {

        User user = new User();
        user.setRole("USER");
        user.setPassword(r.getPassword());
        user.setRole("USER");

        user = new User(null, r.getName(), r.getEmail(), r.getPassword(), "USER");

        return ResponseEntity.ok(userService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest r) {

        User user = userService.findByEmail(r.getEmail());
        if (user == null) {
            return ResponseEntity.status(401).build();
        }

        if (!new BCryptPasswordEncoder().matches(r.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).build();
        }

        String token = jwt.createToken(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
