package com.DevelopersGroupINU.Images_And_Combinations.service;

import com.DevelopersGroupINU.Images_And_Combinations.entity.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemsServiceImpl implements OrderItemsService{
    @Override
    public Void save(OrderItem item) {
        return null;
    }

    @Override
    public OrderItem findById(Long id) {
        return null;
    }

    @Override
    public Void deleteById(Long id) {
        return null;
    }

    @Override
    public OrderItem update(OrderItem item) {
        return null;
    }

    @Override
    public List<OrderItem> findAll() {
        return List.of();
    }
}
