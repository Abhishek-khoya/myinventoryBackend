package com.inventory.management.MyInventory.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class homeController {
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/home")
    public String home()
    {
        return "this is home page";
    }

}
