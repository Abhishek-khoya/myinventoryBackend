package com.inventory.management.MyInventory.repositories;

import com.inventory.management.MyInventory.pojo.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Categories,Long> {
}
