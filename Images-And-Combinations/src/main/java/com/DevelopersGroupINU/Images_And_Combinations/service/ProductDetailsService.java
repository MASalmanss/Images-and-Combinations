package com.DevelopersGroupINU.Images_And_Combinations.service;

import com.DevelopersGroupINU.Images_And_Combinations.entity.Category;
import com.DevelopersGroupINU.Images_And_Combinations.entity.Product;
import com.DevelopersGroupINU.Images_And_Combinations.entity.ProductDetail;

import java.util.List;

public interface ProductDetailsService {

    Void save(ProductDetail productDetail);
    ProductDetail findById(Long id);
    Void deleteById(Long id);
    ProductDetail update(ProductDetail productDetail);
    List<ProductDetail> findAll();

}
