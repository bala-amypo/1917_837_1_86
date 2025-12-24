// FarmRequest.java
package com.example.demo.dto;
import lombok.*;

@Data @AllArgsConstructor
public class FarmRequest {
    private String name;
    private double soilPH;
    private double waterLevel;
    private String season;
}
