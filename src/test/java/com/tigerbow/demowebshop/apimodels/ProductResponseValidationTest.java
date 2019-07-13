package com.tigerbow.demowebshop.apimodels;

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

public class ProductResponseValidationTest {

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
        ProductResponse product = new ProductResponse(1L, "Red Wine", new BigDecimal("100"));
        // when
        Set<ConstraintViolation<ProductResponse>> violations = validator.validate(product);
        // then
        assertTrue(violations.isEmpty());
    }

    @Test
    public void idIsNull() {
        // given
        ProductResponse product = new ProductResponse(null, "Red Wine", new BigDecimal("100"));
        // when
        Set<ConstraintViolation<ProductResponse>> violations = validator.validate(product);
        // then
        assertEquals(1, violations.size());
        ConstraintViolation<ProductResponse> violation = violations.iterator().next();
        assertEquals("id", violation.getPropertyPath().toString());
        assertEquals(null, violation.getInvalidValue());
    }

}
