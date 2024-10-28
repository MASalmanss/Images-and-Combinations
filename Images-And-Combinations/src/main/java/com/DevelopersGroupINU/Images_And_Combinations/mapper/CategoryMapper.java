package com.DevelopersGroupINU.Images_And_Combinations.mapper;

import com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos.CategoryCreateDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.responseDtos.CategoryViewDto;
import com.DevelopersGroupINU.Images_And_Combinations.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface CategoryMapper {
    Category dtoToEntity(CategoryCreateDto categoryCreateDto);
    CategoryViewDto entityToDto(Category category);
}
