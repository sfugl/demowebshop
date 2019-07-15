package com.tigerbow.demowebshop.controllers;

import com.tigerbow.demowebshop.apimodels.OrderRequest;
import com.tigerbow.demowebshop.apimodels.OrderResponse;
import com.tigerbow.demowebshop.entities.OrderEntity;
import com.tigerbow.demowebshop.repositories.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.OffsetDateTime;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    @Autowired
    private OrderRepository repository;

    private final ModelMapper modelMapper = new ModelMapper();

    @PostMapping()
    OrderResponse create(@Valid @RequestBody OrderRequest orderRequest) {
        OrderEntity orderEntity = modelMapper.map(orderRequest, OrderEntity.class);
        orderEntity.setOrderTime(OffsetDateTime.now());
        orderEntity = repository.save(orderEntity);
        return modelMapper.map(orderEntity, OrderResponse.class);
    }

}
