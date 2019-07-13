package com.tigerbow.demowebshop.apimodels;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

public class OrderResponse {

    private Long id;
    private String email;
    private List<OrderLine> orderLines;
    private BigDecimal totalPrice;
    private ZonedDateTime orderTime;

}
