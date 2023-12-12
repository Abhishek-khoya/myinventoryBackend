package com.inventory.management.MyInventory.pojo;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Setter
@Getter
@Entity(name="sale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sale_id")
    private long saleId;
    @Column(name="product_id")
    private long productId;
    @Column(name="sale_date")
    private Date saleDate;
    @Column(name="quantity_sold")
    private long quantitySold;
    @Column(name="total_ammount")
    private BigDecimal totalAmmount;
}
