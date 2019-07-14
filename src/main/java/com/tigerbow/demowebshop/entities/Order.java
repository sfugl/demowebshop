package com.tigerbow.demowebshop.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * Entity for persisting orders to a database
 */

@Entity
@Table(name = "\"order\"")
@Data
@AllArgsConstructor
public class Order {

    private @Id @GeneratedValue Long id;
    private @Size(max = 64) @NotNull String email;
    @ElementCollection
    @CollectionTable(name = "order_lines", joinColumns = @JoinColumn(name = "order_id"))
    private @NotNull List<OrderLine> orderLines;
    private @Column(precision = 10, scale = 2) @NotNull BigDecimal totalPrice;
    private @Column(columnDefinition = "timestamp with time zone") @NotNull OffsetDateTime orderTime;

}
