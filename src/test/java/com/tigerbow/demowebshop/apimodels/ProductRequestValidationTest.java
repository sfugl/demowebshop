package com.tigerbow.demowebshop.apimodels;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.Set;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class ProductRequestValidationTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeClass
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void noValidationErrors() {
        // given
        ProductRequest product = new ProductRequest("Red Wine", new BigDecimal("100"));
        // when
        Set<ConstraintViolation<ProductRequest>> violations = validator.validate(product);
        // then
        assertTrue(violations.isEmpty());
    }

    @Test
    public void nameIsNull() {
        // given
        ProductRequest product = new ProductRequest(null, new BigDecimal("100"));
        // when
        Set<ConstraintViolation<ProductRequest>> violations = validator.validate(product);
        // then
        assertEquals(1, violations.size());
        ConstraintViolation<ProductRequest> violation = violations.iterator().next();
        assertEquals("name", violation.getPropertyPath().toString());
        assertEquals(null, violation.getInvalidValue());
    }

    @Test
    public void priceIsNull() {
        // given
        ProductRequest product = new ProductRequest("Red Wine", null);
        // when
        Set<ConstraintViolation<ProductRequest>> violations = validator.validate(product);
        // then
        assertEquals(1, violations.size());
        ConstraintViolation<ProductRequest> violation = violations.iterator().next();
        assertEquals("price", violation.getPropertyPath().toString());
        assertEquals(null, violation.getInvalidValue());
    }

    @Test
    public void priceToBig() {
        // given
        ProductRequest product = new ProductRequest("Red Wine", new BigDecimal("10000000000"));
        // when
        Set<ConstraintViolation<ProductRequest>> violations = validator.validate(product);
        // then
        assertEquals(1, violations.size());
        ConstraintViolation<ProductRequest> violation = violations.iterator().next();
        assertEquals("price", violation.getPropertyPath().toString());
        assertEquals(new BigDecimal("10000000000"), violation.getInvalidValue());
    }

    @Test
    public void priceIllegalFraction() {
        // given
        ProductRequest product = new ProductRequest("Red Wine", new BigDecimal("100.000"));
        // when
        Set<ConstraintViolation<ProductRequest>> violations = validator.validate(product);
        // then
        assertEquals(1, violations.size());
        ConstraintViolation<ProductRequest> violation = violations.iterator().next();
        assertEquals("price", violation.getPropertyPath().toString());
        assertEquals(new BigDecimal("100.000"), violation.getInvalidValue());
    }

    @AfterClass
    public static void close() {
        validatorFactory.close();
    }

}
