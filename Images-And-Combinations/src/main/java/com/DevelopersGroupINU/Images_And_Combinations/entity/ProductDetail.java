package com.DevelopersGroupINU.Images_And_Combinations.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String size;
    private String material;
    private String brand;
    private double price;
    private String imageName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
