package com.DevelopersGroupINU.Images_And_Combinations.controller;

import com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos.OrderCreateDto;
import com.DevelopersGroupINU.Images_And_Combinations.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrdersService ordersService;

    @PostMapping("")
    public ResponseEntity<Void> save(@RequestBody OrderCreateDto orderCreateDto){
        ordersService.save(orderCreateDto);
        return ResponseEntity.ok().build();
    }
}
