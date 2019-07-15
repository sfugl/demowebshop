package com.tigerbow.demowebshop.entities;

import com.tigerbow.demowebshop.repositories.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductEntityJpaTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository repository;

    @Test
    public void testCreateAndFetch() {
        ProductEntity productEntity = new ProductEntity(null,"Candy", new BigDecimal("12.95"));
        entityManager.persist(productEntity);
        List<ProductEntity> productEntities = repository.findAll();
        assertEquals(1, productEntities.size());
        assertEquals(productEntity, productEntities.get(0));
    }

}
