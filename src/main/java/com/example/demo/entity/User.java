package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class User {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private String password;
    private String role;

    public User() {}

    public User(Long id, String name, String email, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getRole() { return role; }

    public void setPassword(String password) { this.password = password; }
    public void setRole(String role) { this.role = role; }
}
