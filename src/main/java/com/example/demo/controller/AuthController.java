package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.demo.security.JwtTokenProvider;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwt;

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest req) {
        userService.register(User.builder()
                .name(req.getName())
                .email(req.getEmail())
                .password(req.getPassword())
                .build());
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest req) {
        User user = userService.findByEmail(req.getEmail());
        String token = jwt.createToken(user.getId(), user.getEmail(), user.getRole());
        return new AuthResponse(token);
    }
}
