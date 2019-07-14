package com.tigerbow.demowebshop.apimodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * Model api object for product input
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @NotNull
    @Size(min = 1, max = 32)
    private String name;
    @NotNull
    @Digits(integer = 10, fraction = 2)
    private BigDecimal price;

}
