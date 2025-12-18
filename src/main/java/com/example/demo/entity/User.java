package com.example.demo.entity;

import jakarta.persistence.*;

// @Entity
// @Data
// @Builder
// @NoArgsConstructor
// @AllArgsConstructor
public class User {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // @Column(unique = true)
    private String email;

    private String password;

    private String role;
}
