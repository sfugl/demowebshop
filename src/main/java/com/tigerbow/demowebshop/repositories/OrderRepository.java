package com.tigerbow.demowebshop.repositories;

import com.tigerbow.demowebshop.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
}
