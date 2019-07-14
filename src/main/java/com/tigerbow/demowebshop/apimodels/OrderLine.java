package com.tigerbow.demowebshop.apimodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Model api object for order line input and output
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLine {

    @NotNull
    private BigDecimal amount;
    @NotNull
    private ProductResponse product;

}
