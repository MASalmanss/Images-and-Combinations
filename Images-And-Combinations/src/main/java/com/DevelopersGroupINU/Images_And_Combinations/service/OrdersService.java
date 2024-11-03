package com.DevelopersGroupINU.Images_And_Combinations.service;

import com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos.OrderCreateDto;
import com.DevelopersGroupINU.Images_And_Combinations.entity.Category;
import com.DevelopersGroupINU.Images_And_Combinations.entity.Orders;

import java.util.List;

public interface OrdersService {
    Void save(OrderCreateDto orderCreateDto);
    Orders findById(Long id);
    Void deleteById(Long id);
    Orders update(Orders orders);
    List<Orders> findAll();

}
