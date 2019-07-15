package com.tigerbow.demowebshop.repositories;

import com.tigerbow.demowebshop.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
}
