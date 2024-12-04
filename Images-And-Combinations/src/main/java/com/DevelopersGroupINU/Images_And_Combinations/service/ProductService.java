package com.DevelopersGroupINU.Images_And_Combinations.service;

import com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos.ProductCreateDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos.ProductUpdateDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.responseDtos.ProductViewDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    ProductViewDto save(ProductCreateDto productCreateDto);
    ProductViewDto findById(Long id);
    Void deleteById(Long id);
    ProductViewDto update(ProductUpdateDto productUpdateDto , Long id);
    List<ProductViewDto> findAll();
    Boolean saveImg(MultipartFile file , Long id);

}
