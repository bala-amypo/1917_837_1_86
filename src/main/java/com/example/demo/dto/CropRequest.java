// CropRequest.java
package com.example.demo.dto;
import lombok.*;

@Data @AllArgsConstructor
public class CropRequest {
    private String name;
    private double phMin;
    private double phMax;
    private double water;
    private String season;
}
