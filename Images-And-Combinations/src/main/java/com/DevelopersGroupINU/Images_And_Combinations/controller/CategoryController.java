package com.DevelopersGroupINU.Images_And_Combinations.controller;

import com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos.CategoryCreateDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.responseDtos.CategoryViewDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.responseDtos.ProductViewDto;
import com.DevelopersGroupINU.Images_And_Combinations.entity.Category;
import com.DevelopersGroupINU.Images_And_Combinations.entity.Product;
import com.DevelopersGroupINU.Images_And_Combinations.service.CategoryService;
import com.DevelopersGroupINU.Images_And_Combinations.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<Void> save(@RequestBody CategoryCreateDto categoryCreateDto){
        categoryService.save(categoryCreateDto);
        return ResponseEntity.ok().build();
    }

    /*@PutMapping("/{id}")
    public ResponseEntity

     */

    @GetMapping("/{id}")
    public ResponseEntity<CategoryViewDto> findById(@PathVariable Long id){
       CategoryViewDto category = categoryService.findById(id);
       return ResponseEntity.ok(category);
    }

    @GetMapping("")
    public ResponseEntity<List<CategoryViewDto>> findAll(){
        List<CategoryViewDto> liste = categoryService.findAll();
        return ResponseEntity.ok(liste);
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<List<ProductViewDto>> findAllProductsByCategory(@PathVariable Long id){
       return ResponseEntity.ok(categoryService.findAllProductsByCategory(id));
    }

}
