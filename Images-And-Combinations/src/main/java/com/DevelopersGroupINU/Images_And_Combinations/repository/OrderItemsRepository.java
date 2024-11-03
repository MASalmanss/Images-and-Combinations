package com.DevelopersGroupINU.Images_And_Combinations.repository;

import com.DevelopersGroupINU.Images_And_Combinations.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItem , Long> {
}
