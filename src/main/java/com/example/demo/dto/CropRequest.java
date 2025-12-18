// dto/CropRequest.java
package com.example.demo.dto;

// @Data @Builder @NoArgsConstructor @AllArgsConstructor
public class CropRequest {
    private String name;
    private Double suitablePHMin;
    private Double suitablePHMax;
    private Double requiredWater;
    private String season;
}
