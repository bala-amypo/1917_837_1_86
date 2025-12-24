// RegisterRequest.java
package com.example.demo.dto;
import lombok.*;

@Data @AllArgsConstructor
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
}
