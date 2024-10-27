package com.DevelopersGroupINU.Images_And_Combinations.service;

import com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos.ProductCreateDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos.ProductUpdateDto;
import com.DevelopersGroupINU.Images_And_Combinations.entity.Product;
import com.DevelopersGroupINU.Images_And_Combinations.entity.ProductDetail;
import com.DevelopersGroupINU.Images_And_Combinations.repository.ProductDetailsRepository;
import com.DevelopersGroupINU.Images_And_Combinations.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductDetailsRepository productDetailsRepository;

    @Override
    public Void save(ProductCreateDto productCreateDto) {
        Product product = new Product();
        ProductDetail productDetail = new ProductDetail();
        productDetail.setBrand(productCreateDto.getBrand());
        productDetail.setPrice(productCreateDto.getPrice());
        productDetail.setSize(productCreateDto.getSize());
        productDetail.setMaterial(productCreateDto.getMaterial());
        product.setName(productCreateDto.getName());
        productDetail.setProduct(product);
        product.setProductDetail(productDetail);
        productRepository.save(product);
        return null;
    }

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public Void deleteById(Long id) {
        return null;
    }

    @Override
    public Product update(ProductUpdateDto productUpdateDto) {
        return null;
    }


    @Override
    public List<Product> findAll() {
        return List.of();
    }
}
