package com.DevelopersGroupINU.Images_And_Combinations.controller;


import com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos.ProductCreateDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos.ProductUpdateDto;
import com.DevelopersGroupINU.Images_And_Combinations.entity.Product;
import com.DevelopersGroupINU.Images_And_Combinations.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
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
    public ResponseEntity<Void> save(@RequestBody ProductCreateDto productCreateDto){

        if(productCreateDto.getCategoryId() == null){
            return ResponseEntity.badRequest().build();
        }

        productService.save(productCreateDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("")
    public ResponseEntity<Product> update(@RequestBody ProductUpdateDto productUpdateDto){
        var nesne = productService.update(productUpdateDto);
        return ResponseEntity.ok(nesne);
    }

    @PostMapping("/file/{id}")
    public ResponseEntity<Boolean> fileUpdate(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(false);
        }
        Boolean sonuc =productService.saveImg(file , id);
        if (sonuc){
            return ResponseEntity.status(200).build();
        }
        else {
            return ResponseEntity.status(200).build();
        }


    }



}
