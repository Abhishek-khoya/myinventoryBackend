package com.inventory.management.MyInventory.pojo;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private long productId;
    @Column(name="product_name")
    private String productName;
    @Column(name="description")
    private String description;
    @Column(name = "quantity")
    private long quantity;
    @Column(name="price")
    private BigDecimal price;
    @Column(name="category_id")
    private long categoryId;
}
