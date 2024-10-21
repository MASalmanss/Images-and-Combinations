package com.DevelopersGroupINU.Images_And_Combinations.service;

import com.DevelopersGroupINU.Images_And_Combinations.entity.Category;

import java.util.List;

public interface CategoryService {
    Void save(Category category);
    Category findById(Long id);
    Void deleteById(Long id);
    Category update(Category category);
    List<Category> findAll();
}
