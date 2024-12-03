package com.DevelopersGroupINU.Images_And_Combinations.service;

import com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos.CategoryCreateDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.responseDtos.CategoryViewDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.responseDtos.ProductViewDto;
import com.DevelopersGroupINU.Images_And_Combinations.entity.Category;

import java.util.List;

public interface CategoryService {
    Void save(CategoryCreateDto categoryCreateDto);
    CategoryViewDto findById(Long id);
    Void deleteById(Long id);
    Category update(Category category);
    List<CategoryViewDto> findAll();
    List<ProductViewDto> findAllProductsByCategory(Long id);
}
