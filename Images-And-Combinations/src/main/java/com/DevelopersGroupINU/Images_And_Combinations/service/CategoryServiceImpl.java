package com.DevelopersGroupINU.Images_And_Combinations.service;

import com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos.CategoryCreateDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.responseDtos.CategoryViewDto;
import com.DevelopersGroupINU.Images_And_Combinations.entity.Category;
import com.DevelopersGroupINU.Images_And_Combinations.mapper.CategoryMapper;
import com.DevelopersGroupINU.Images_And_Combinations.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    @Override
    public Void save(CategoryCreateDto categoryCreateDto) {
        Category category = categoryMapper.dtoToEntity(categoryCreateDto);
        categoryRepository.save(category);
        return null;
    }

    @Override
    public CategoryViewDto findById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Bad request"));
        return categoryMapper.entityToDto(category);
    }

    @Override
    public Void deleteById(Long id) {
        return null;
    }

    @Override
    public Category update(Category category) {
        return null;
    }

    @Override
    public List<CategoryViewDto> findAll() {
        var list = categoryRepository.findAll();
        List<CategoryViewDto> liste = categoryMapper.entityListTodtoList(list);
        return liste;
    }
}
