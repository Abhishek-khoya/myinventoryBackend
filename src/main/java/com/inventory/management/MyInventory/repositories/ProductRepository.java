package com.inventory.management.MyInventory.repositories;

import com.inventory.management.MyInventory.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
