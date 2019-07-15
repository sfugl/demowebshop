package com.tigerbow.demowebshop.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Entity for persisting order lines to a database
 */

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineEntity {

    private @Column(precision = 10, scale = 2) @NotNull BigDecimal amount;
    private @Embedded @NotNull OrderLineProductEntity product;

}
