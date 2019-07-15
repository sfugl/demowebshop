package com.tigerbow.demowebshop.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tigerbow.demowebshop.apimodels.ProductRequest;
import com.tigerbow.demowebshop.apimodels.ProductResponse;
import com.tigerbow.demowebshop.entities.ProductEntity;
import com.tigerbow.demowebshop.repositories.ProductRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository repository;

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
        ProductRequest productRequest = new ProductRequest("Red Wine", new BigDecimal("99.95"));
        ProductResponse productResponse = new ProductResponse(1L, "Red Wine", new BigDecimal("99.95"));
        given(repository.save(new ProductEntity(null, "Red Wine", new BigDecimal("99.95"))))
            .willReturn(new ProductEntity(1L, "Red Wine", new BigDecimal("99.95")));
        mockMvc.perform(post("/api/v1/products")
            .accept(APPLICATION_JSON)
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(productRequest)))
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(productResponse)))
            .andDo(document("create-product-ok"));
    }

    @Test
    public void listAll() throws Exception {
        List<ProductResponse> result = Arrays.asList(new ProductResponse(1L, "Red Wine", new BigDecimal("99.95")));
        given(repository.findAll()).willReturn(Arrays.asList(new ProductEntity(1L, "Red Wine", new BigDecimal("99.95"))));
        mockMvc.perform(get("/api/v1/products"))
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(result)))
            .andDo(document("list-all-products-ok"));
    }

}
