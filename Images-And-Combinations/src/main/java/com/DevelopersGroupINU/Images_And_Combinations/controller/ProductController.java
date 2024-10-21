package com.DevelopersGroupINU.Images_And_Combinations.controller;


import com.DevelopersGroupINU.Images_And_Combinations.entity.Product;
import com.DevelopersGroupINU.Images_And_Combinations.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        var product = productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("")
    public List<Product> findAll(){
        return productService.findAll();
    }

    @PostMapping("")
    public ResponseEntity<Void> save(@RequestBody Product product){
        productService.save(product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("")
    public ResponseEntity<Product> update(@RequestBody Product product){
        var nesne = productService.update(product);
        return ResponseEntity.ok(nesne);
    }
}
