package com.example.demo;

public class UserServiceImpl
        extends com.example.demo.service.impl.UserServiceImpl {

    public UserServiceImpl(
            com.example.demo.repository.UserRepository r,
            org.springframework.security.crypto.password.PasswordEncoder e) {
        super(r, e);
    }
}
