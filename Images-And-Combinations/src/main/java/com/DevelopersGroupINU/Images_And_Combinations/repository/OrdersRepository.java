package com.DevelopersGroupINU.Images_And_Combinations.repository;

import com.DevelopersGroupINU.Images_And_Combinations.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders , Long> {
}
