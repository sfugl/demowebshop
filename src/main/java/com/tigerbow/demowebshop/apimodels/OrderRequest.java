package com.tigerbow.demowebshop.apimodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * Model api object for order input
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    @NotNull
    @Email
    private String email;
    @NotEmpty
    private List<OrderLine> orderLines;

    public BigDecimal getTotalPrice() {
        BigDecimal result = new BigDecimal("0");
        for (OrderLine orderLine : orderLines)
            result = result.add(orderLine.getAmount().multiply(orderLine.getProduct().getPrice()));
        return result;
    }

}
