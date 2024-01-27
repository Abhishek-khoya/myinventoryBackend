package com.inventory.management.MyInventory.controllers;

import com.inventory.management.MyInventory.pojo.Categories;
import com.inventory.management.MyInventory.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CategoriesController {
    @Autowired
    private CategoriesRepository categoriesRepository;
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/categories")
    public Iterable<Categories> getAllCategories()
    {
        return categoriesRepository.findAll();
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/categories")
    public ResponseEntity<Categories> addCategories(@RequestBody Categories categories)
    {
        Categories newCategories=categoriesRepository.save(categories);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategories);
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/categories/{id}")
    public ResponseEntity<Categories> getCategoryById(@PathVariable long id)
    {
        Optional<Categories> optionalCategory=categoriesRepository.findById(id);
        if(optionalCategory.isPresent())
        {
            Categories presentCategories=optionalCategory.get();
            return ResponseEntity.status(HttpStatus.FOUND).body(presentCategories);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/categories/{id}")
    public ResponseEntity<Categories> updateCategories(@PathVariable long id,@RequestBody Categories categories)
    {
        Optional<Categories> optionalCategories=categoriesRepository.findById(id);
        if(optionalCategories.isPresent())
        {
            Categories presentCategories=optionalCategories.get();
            presentCategories.setCategoryName(categories.getCategoryName());
            categoriesRepository.save(presentCategories);
            return ResponseEntity.status(HttpStatus.CREATED).body(presentCategories);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Categories> deleteCategories(@PathVariable long id)
    {
        Optional<Categories> optionalCategories=categoriesRepository.findById(id);
        if(optionalCategories.isPresent())
        {
            categoriesRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalCategories.get());
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
