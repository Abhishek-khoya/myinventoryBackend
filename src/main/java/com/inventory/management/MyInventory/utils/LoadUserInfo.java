package com.inventory.management.MyInventory.utils;

import com.inventory.management.MyInventory.pojo.UserInfo;
import com.inventory.management.MyInventory.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class LoadUserInfo implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo=userRepository.findByUsername(username);
        return userInfo.map(UserInfoDetails::new).orElseThrow(()->new UsernameNotFoundException("User Not Found "+username));
        //return null;
    }
}
