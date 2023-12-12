package com.inventory.management.MyInventory.pojo;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Setter
@Getter
@Entity(name = "photos")
public class Photos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="photo_id")
    private long photoId;
    @Column(name="product_id")
    private long productId;
    @Column(name="photo")
    private byte[] photo;
}
