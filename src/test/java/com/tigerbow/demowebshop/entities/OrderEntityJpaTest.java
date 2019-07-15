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
public class OrderEntityJpaTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrderRepository repository;

    @Test
    public void testCreateAndFetch() {
        OrderLineEntity orderLineEntity = new OrderLineEntity(new BigDecimal("1"), new OrderLineProductEntity(1L, "Candy", new BigDecimal("10.10")));
        OrderEntity orderEntity = new OrderEntity(null,"bob@gmail.com", Arrays.asList(orderLineEntity), new BigDecimal("10.10"), OffsetDateTime.now());
        entityManager.persist(orderEntity);
        List<OrderEntity> orderEntities = repository.findAll();
        assertEquals(1, orderEntities.size());
        assertEquals(orderEntity, orderEntities.get(0));
    }

}