package com.DevelopersGroupINU.Images_And_Combinations.service;

import com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos.ProductCreateDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos.ProductUpdateDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.responseDtos.ProductViewDto;
import com.DevelopersGroupINU.Images_And_Combinations.entity.Category;
import com.DevelopersGroupINU.Images_And_Combinations.entity.Orders;
import com.DevelopersGroupINU.Images_And_Combinations.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    Void save(ProductCreateDto productCreateDto);
    Product findById(Long id);
    Void deleteById(Long id);
    Product update(ProductUpdateDto productUpdateDto);
    List<ProductViewDto> findAll();
    Boolean saveImg(MultipartFile file , Long id);

}
