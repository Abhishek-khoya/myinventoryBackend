package com.inventory.management.MyInventory.repositories;

import com.inventory.management.MyInventory.pojo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfo,Long> {

}
