package com.tigerbow.demowebshop.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tigerbow.demowebshop.apimodels.OrderLine;
import com.tigerbow.demowebshop.apimodels.OrderRequest;
import com.tigerbow.demowebshop.apimodels.OrderResponse;
import com.tigerbow.demowebshop.apimodels.ProductResponse;
import com.tigerbow.demowebshop.entities.OrderEntity;
import com.tigerbow.demowebshop.entities.OrderLineEntity;
import com.tigerbow.demowebshop.entities.OrderLineProductEntity;
import com.tigerbow.demowebshop.repositories.OrderRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;

import static org.mockito.BDDMockito.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
            .apply(documentationConfiguration(restDocumentation))
            .build();
    }

    @Test
    public void create() throws Exception {
        // given
        OffsetDateTime now = OffsetDateTime.now();
        OrderRequest orderRequest = new OrderRequest("bob@gmail.com", Arrays.asList(new OrderLine(new BigDecimal("1"), new ProductResponse(1L, "Red Wine", new BigDecimal("100")))));
        OrderResponse orderResponse = new OrderResponse(1L, "bob@gmail.com", Arrays.asList(new OrderLine(new BigDecimal("1"), new ProductResponse(1L, "Red Wine", new BigDecimal("100")))), new BigDecimal("100"), now);

        // mock
        OrderEntity orderEntityReturn = new OrderEntity(1L, "bob@gmail.com", Arrays.asList(new OrderLineEntity(new BigDecimal("1"), new OrderLineProductEntity(1L, "Red Wine", new BigDecimal("100")))), new BigDecimal("100"), now);
        when(repository.save(any())).thenReturn(orderEntityReturn);

        // then
        mockMvc.perform(post("/api/v1/orders")
            .accept(APPLICATION_JSON)
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(orderRequest)))
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(orderResponse)))
            .andDo(document("create-order-ok"));
    }

}