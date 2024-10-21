package com.DevelopersGroupINU.Images_And_Combinations.service;

import com.DevelopersGroupINU.Images_And_Combinations.entity.Category;
import com.DevelopersGroupINU.Images_And_Combinations.entity.OrderItem;
import com.DevelopersGroupINU.Images_And_Combinations.entity.Orders;

import java.util.List;

public interface OrderItemsService {

    Void save(OrderItem item);
    OrderItem findById(Long id);
    Void deleteById(Long id);
    OrderItem update(OrderItem item);
    List<OrderItem> findAll();

}
