// AuthRequest.java
package com.example.demo.dto;
import lombok.*;

@Data @AllArgsConstructor
public class AuthRequest {
    private String email;
    private String password;
}
