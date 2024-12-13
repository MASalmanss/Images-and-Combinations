package com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos;

import lombok.Data;

@Data
public class ProductUpdateDto {
    private String size;
    private String name;
    private String material;
    private String brand;
    private double price;
    private String gender;
    private String ageStage;
}

