package com.DevelopersGroupINU.Images_And_Combinations.repository;

import com.DevelopersGroupINU.Images_And_Combinations.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product , Long> {
}
