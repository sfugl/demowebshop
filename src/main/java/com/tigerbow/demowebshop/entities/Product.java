package com.tigerbow.demowebshop.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * Entity for persisting products to a database
 */

@Entity
@Data
@AllArgsConstructor
public class Product {

    private @Id @GeneratedValue Long id;
    private @Size(max = 32) @NotNull String name;
    private @Column(precision = 10, scale = 2) @NotNull BigDecimal price;

}
