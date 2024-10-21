package com.DevelopersGroupINU.Images_And_Combinations.service;

import com.DevelopersGroupINU.Images_And_Combinations.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Override
    public Void save(Product product) {
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
    public Product update(Product product) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        return List.of();
    }
}
