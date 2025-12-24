package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository r, PasswordEncoder e) {
        repo = r;
        encoder = e;
    }

    @Override
    public User register(User u) {
        if (repo.findByEmail(u.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }
        u.setPassword(encoder.encode(u.getPassword()));
        return repo.save(u);
    }

    @Override
    public User findByEmail(String email) {
        return repo.findByEmail(email).orElse(null);
    }

    @Override
    public User findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new BadRequestException("User not found"));
    }
}
