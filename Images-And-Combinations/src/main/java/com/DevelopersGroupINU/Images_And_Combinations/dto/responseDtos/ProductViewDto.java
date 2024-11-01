package com.DevelopersGroupINU.Images_And_Combinations.dto.responseDtos;

import lombok.Data;

@Data
public class ProductViewDto {
    private Long id;
    private String name;
    private String size;
    private String material;
    private String brand;
    private double price;
}
