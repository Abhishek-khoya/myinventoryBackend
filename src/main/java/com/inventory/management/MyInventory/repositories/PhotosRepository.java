package com.inventory.management.MyInventory.repositories;

import com.inventory.management.MyInventory.pojo.Photos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhotosRepository extends JpaRepository<Photos,Long> {
    public Optional<Photos> getByProductId(long id);
}
