package com.tigerbow.demowebshop.apimodels;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

public class OrderRequestTest {

    @Test
    public void totalPrice() {
        // given
        OrderLine orderLine1 = new OrderLine(new BigDecimal(1),
                new ProductResponse(1L, "Red Wine", new BigDecimal("100")));
        OrderLine orderLine2 = new OrderLine(new BigDecimal(2),
                new ProductResponse(2L, "White Wine", new BigDecimal("50")));
        OrderRequest orderRequest = new OrderRequest("bob@gmail.com", Arrays.asList(orderLine1, orderLine2));
        // then
        Assert.assertEquals(new BigDecimal("200"), orderRequest.getTotalPrice());
    }

}
