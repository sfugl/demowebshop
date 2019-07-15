package com.tigerbow.demowebshop.apimodels;

import org.assertj.core.util.Lists;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Set;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert. assertEquals;

public class OrderRequestValidationTest {

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
        OrderLine orderLine = new OrderLine(new BigDecimal(1),
            new ProductResponse(1L, "Red Wine", new BigDecimal("100")));
        OrderRequest order = new OrderRequest("bob@email.com", Lists.newArrayList(orderLine));
        // when
        Set<ConstraintViolation<OrderRequest>> violations = validator.validate(order);
        // then
        assertTrue(violations.isEmpty());
    }

    @Test
    public void emailIsNull() {
        // given
        OrderLine orderLine = new OrderLine(new BigDecimal(1),
                new ProductResponse(1L, "Red Wine", new BigDecimal("100")));
        OrderRequest order = new OrderRequest(null, Lists.newArrayList(orderLine));
        // when
        Set<ConstraintViolation<OrderRequest>> violations = validator.validate(order);
        // then
        assertEquals(1, violations.size());
        ConstraintViolation<OrderRequest> violation = violations.iterator().next();
        assertEquals("email", violation.getPropertyPath().toString());
        assertEquals(null, violation.getInvalidValue());
    }

    @Test
    public void illegalEmail() {
        // given
        OrderLine orderLine = new OrderLine(new BigDecimal(1),
                new ProductResponse(1L, "Red Wine", new BigDecimal("100")));
        OrderRequest order = new OrderRequest("illegal", Lists.newArrayList(orderLine));
        // when
        Set<ConstraintViolation<OrderRequest>> violations = validator.validate(order);
        // then
        assertEquals(1, violations.size());
        ConstraintViolation<OrderRequest> violation = violations.iterator().next();
        assertEquals("email", violation.getPropertyPath().toString());
        assertEquals("illegal", violation.getInvalidValue());
    }

    @Test
    public void orderLinesIsNull() {
        // given
        OrderRequest order = new OrderRequest("bob@email.com", null);
        // when
        Set<ConstraintViolation<OrderRequest>> violations = validator.validate(order);
        // then
        assertEquals(1, violations.size());
        ConstraintViolation<OrderRequest> violation = violations.iterator().next();
        assertEquals("orderLines", violation.getPropertyPath().toString());
        assertEquals(null, violation.getInvalidValue());
    }

    @Test
    public void orderLinesIsEmpty() {
        // given
        OrderRequest order = new OrderRequest("bob@email.com", new ArrayList<>());
        // when
        Set<ConstraintViolation<OrderRequest>> violations = validator.validate(order);
        // then
        assertEquals(1, violations.size());
        ConstraintViolation<OrderRequest> violation = violations.iterator().next();
        assertEquals("orderLines", violation.getPropertyPath().toString());
        assertEquals(new ArrayList<>(), violation.getInvalidValue());
    }

    @AfterClass
    public static void close() {
        validatorFactory.close();
    }

}
