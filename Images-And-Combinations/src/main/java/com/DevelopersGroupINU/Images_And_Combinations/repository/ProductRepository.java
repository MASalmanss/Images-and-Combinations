package com.DevelopersGroupINU.Images_And_Combinations.repository;

import com.DevelopersGroupINU.Images_And_Combinations.entity.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);

    @EntityGraph(attributePaths = {"productDetail"})
    Product findWithProductDetailById(Long id);

    @Query("SELECT p FROM Product p WHERE p.gender = :gender AND p.ageStage = :ageStage")
    List<Product> findByGenderAndAgeStage(String gender, String ageStage);
}
