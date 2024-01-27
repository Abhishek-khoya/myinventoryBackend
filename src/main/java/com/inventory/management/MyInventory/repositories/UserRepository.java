package com.inventory.management.MyInventory.repositories;

import com.inventory.management.MyInventory.pojo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo,Long> {
    public Optional<UserInfo> findByUsername(String name);

}
