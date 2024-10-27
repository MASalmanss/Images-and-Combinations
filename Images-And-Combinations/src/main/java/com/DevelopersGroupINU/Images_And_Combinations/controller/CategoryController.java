package com.DevelopersGroupINU.Images_And_Combinations.controller;

import com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos.CategoryCreateDto;
import com.DevelopersGroupINU.Images_And_Combinations.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<Void> save(CategoryCreateDto categoryCreateDto){
        categoryService.save(categoryCreateDto);
        return ResponseEntity.ok().build();
    }


}
