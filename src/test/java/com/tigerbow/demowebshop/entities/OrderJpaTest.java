package com.tigerbow.demowebshop.entities;

import com.tigerbow.demowebshop.repositories.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderJpaTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrderRepository repository;

    @Test
    public void testCreateAndFetch() {
        OrderLine orderLine = new OrderLine(new BigDecimal("1"), 1L, "Candy", new BigDecimal("10.10"));
        Order order = new Order(null,"bob@gmail.com", Arrays.asList(orderLine), new BigDecimal("10.10"), OffsetDateTime.now());
        entityManager.persist(order);
        List<Order> orders = repository.findAll();
        assertEquals(1, orders.size());
        assertEquals(order, orders.get(0));
    }

}