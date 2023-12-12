package com.inventory.management.MyInventory.repositories;

import com.inventory.management.MyInventory.pojo.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale,Long> {
}
