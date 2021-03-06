package com.tigerbow.demowebshop.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineProductEntity {

    private @NotNull Long productId;
    private @Size(max = 32) @NotNull String name;
    private @Column(precision = 10, scale = 2) @NotNull BigDecimal price;

}
