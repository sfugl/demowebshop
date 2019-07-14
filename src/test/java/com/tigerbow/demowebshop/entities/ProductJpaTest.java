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
public class ProductJpaTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository repository;

    @Test
    public void testCreateAndFetch() {
        Product product = new Product(null,"Candy", new BigDecimal("12.95"));
        entityManager.persist(product);
        List<Product> products = repository.findAll();
        assertEquals(1, products.size());
        assertEquals(product, products.get(0));
    }

}
