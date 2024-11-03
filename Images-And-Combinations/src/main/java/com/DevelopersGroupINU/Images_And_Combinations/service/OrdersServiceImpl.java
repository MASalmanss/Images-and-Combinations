package com.DevelopersGroupINU.Images_And_Combinations.service;

import com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos.OrderCreateDto;
import com.DevelopersGroupINU.Images_And_Combinations.entity.OrderItem;
import com.DevelopersGroupINU.Images_And_Combinations.entity.Orders;
import com.DevelopersGroupINU.Images_And_Combinations.entity.Product;
import com.DevelopersGroupINU.Images_And_Combinations.repository.OrderItemsRepository;
import com.DevelopersGroupINU.Images_And_Combinations.repository.OrdersRepository;
import com.DevelopersGroupINU.Images_And_Combinations.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;
    private final ProductRepository productRepository;
    private final OrderItemsRepository orderItemsRepository;

    @Override
    public Void save(OrderCreateDto orderCreateDto) {
        Orders orders = new Orders();
        orders.setTotalAmount(orderCreateDto.getTotalAmount());
        for (Long id : orderCreateDto.getOrderIdList()){
            OrderItem newItem = new OrderItem();
            newItem.setQuantity(1);
            Product product = productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not found !"));
            newItem.setProduct(product);
            newItem.setOrders(orders);
            orderItemsRepository.save(newItem);
        }
        ordersRepository.save(orders);
        return null;
    }

    @Override
    public Orders findById(Long id) {
        return null;
    }

    @Override
    public Void deleteById(Long id) {
        return null;
    }

    @Override
    public Orders update(Orders orders) {
        return null;
    }

    @Override
    public List<Orders> findAll() {
        return List.of();
    }
}
