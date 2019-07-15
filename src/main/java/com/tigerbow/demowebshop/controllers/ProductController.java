package com.tigerbow.demowebshop.controllers;

import com.tigerbow.demowebshop.apimodels.ProductRequest;
import com.tigerbow.demowebshop.apimodels.ProductResponse;
import com.tigerbow.demowebshop.entities.ProductEntity;
import com.tigerbow.demowebshop.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    private final ModelMapper modelMapper = new ModelMapper();

    @PostMapping()
    ProductResponse create(@Valid @RequestBody ProductRequest productRequest) {
        ProductEntity productEntity = modelMapper.map(productRequest, ProductEntity.class);
        productEntity = repository.save(productEntity);
        return modelMapper.map(productEntity, ProductResponse.class);
    }

    @GetMapping
    List<ProductResponse> listAll() {
        List<ProductEntity> productEntityEntities = repository.findAll();
        return productEntityEntities.stream()
            .map(productEntityEntity -> modelMapper.map(productEntityEntity, ProductResponse.class))
            .collect(toList());
    }

}
