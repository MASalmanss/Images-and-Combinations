package com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos;

import lombok.Data;

import java.util.List;

@Data
public class OrderCreateDto {
    private double totalAmount;
    private Long userId;
    private List<Long> orderIdList;
}
