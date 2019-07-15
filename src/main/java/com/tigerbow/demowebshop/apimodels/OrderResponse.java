package com.tigerbow.demowebshop.apimodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * Model api object for order output
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Long id;
    private String email;
    private List<OrderLine> orderLines;
    private BigDecimal totalOrderPrice;
    private OffsetDateTime orderTime;

}
