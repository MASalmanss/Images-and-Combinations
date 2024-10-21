package com.DevelopersGroupINU.Images_And_Combinations.service;

import com.DevelopersGroupINU.Images_And_Combinations.entity.Category;
import com.DevelopersGroupINU.Images_And_Combinations.entity.Orders;
import com.DevelopersGroupINU.Images_And_Combinations.entity.Product;

import java.util.List;

public interface ProductService {

    Void save(Product product);
    Product findById(Long id);
    Void deleteById(Long id);
    Product update(Product product);
    List<Product> findAll();

}
