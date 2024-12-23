package com.DevelopersGroupINU.Images_And_Combinations.dto.responseDtos;

import lombok.Data;


import lombok.Data;

@Data
public class ProductViewDto {
    private Long id;
    private String name;
    private String size;
    private String material;
    private String brand;
    private double price;
    private String imageName;

    // Yeni eklenen alanlar
    private String gender;
    private String ageStage;
}
