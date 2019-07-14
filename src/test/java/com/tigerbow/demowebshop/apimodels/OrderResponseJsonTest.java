package com.tigerbow.demowebshop.apimodels;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class OrderResponseJsonTest {

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private JacksonTester<OrderResponse> json;

    @Test
    public void testDeserialize() throws Exception {
        OrderLine orderLine = new OrderLine(new BigDecimal("1"), new ProductResponse(1L, "Red Wine", new BigDecimal("200")));
        OrderResponse orderResponse = new OrderResponse(1L, "bob@gmail.com", Arrays.asList(orderLine), new BigDecimal("200"), OffsetDateTime.now());
        String content = json.write(orderResponse).getJson();
        assertThat(json.parse(content)).isEqualTo(orderResponse);
    }

}
