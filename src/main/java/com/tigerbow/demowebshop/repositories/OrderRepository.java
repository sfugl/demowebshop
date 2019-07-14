package com.tigerbow.demowebshop.repositories;

import com.tigerbow.demowebshop.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
