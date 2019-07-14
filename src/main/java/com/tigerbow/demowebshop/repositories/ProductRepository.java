package com.tigerbow.demowebshop.repositories;

import com.tigerbow.demowebshop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
