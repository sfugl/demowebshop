package com.tigerbow.demowebshop.apimodels;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class ProductRequestJsonTest {

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private JacksonTester<ProductRequest> json;

    @Test
    public void testSerialize() throws Exception {
        // given
        ProductRequest product = new ProductRequest("Red Wine", new BigDecimal("99.95"));
        // then
        assertThat(json.write(product)).extractingJsonPathStringValue("@.name").isEqualTo("Red Wine");
        assertThat(json.write(product)).extractingJsonPathNumberValue("@.price").isEqualTo(new BigDecimal("99.95"));
    }

}
