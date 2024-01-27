package com.inventory.management.MyInventory.controllers;
import com.inventory.management.MyInventory.pojo.Photos;
import com.inventory.management.MyInventory.pojo.Product;
import com.inventory.management.MyInventory.repositories.PhotosRepository;
import com.inventory.management.MyInventory.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PhotosRepository photosRepository;
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/products")
    public Iterable<Product> getAllProduct()
    {
        return productRepository.findAll();
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@RequestParam String productName, @RequestParam BigDecimal price,@RequestParam int quantity,@RequestParam String description,@RequestParam int categoryId, @RequestParam("photo")MultipartFile photo)
    {
        Product newProduct=new Product();
        //newProduct.setProductId(product.getProductId());
        newProduct.setProductName(productName);
        newProduct.setPrice(price);
        newProduct.setQuantity(quantity);
        newProduct.setDescription(description);
        newProduct.setCategoryId(categoryId);
        productRepository.save(newProduct);
        Photos photos=new Photos();
        try
        {
            byte[] photoData=photo.getBytes();
            photos.setProductId(newProduct.getProductId());
            photos.setPhoto(photoData);
            photosRepository.save(photos);

        }catch(IOException ioException)
        {
            System.out.println(ioException.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id)
    {
        Optional<Product> optionalProduct=productRepository.findById(id);
        if(optionalProduct.isPresent())
        {
            return ResponseEntity.status(HttpStatus.FOUND).body(optionalProduct.get());
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/products/{id}")
    //public ResponseEntity<Product> updateProduct(@PathVariable long id,@RequestParam String productName, @RequestParam BigDecimal price,@RequestParam int quantity,@RequestParam String description,@RequestParam int categoryId, @RequestParam("photo")MultipartFile photo)
    public ResponseEntity<Product> updateProduct(@PathVariable long id,@RequestBody Product product)
    {
        Optional<Product> optionalProduct=productRepository.findById(id);
        if(optionalProduct.isPresent())
        {
            Product presentProduct=optionalProduct.get();
            presentProduct.setProductName(product.getProductName());
            presentProduct.setPrice(product.getPrice());
            presentProduct.setQuantity(product.getQuantity());
            presentProduct.setDescription(product.getDescription());
            presentProduct.setCategoryId(product.getCategoryId());
            productRepository.save(presentProduct);
            return ResponseEntity.status(HttpStatus.FOUND).body(presentProduct);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("products/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable long id)
    {
        Optional<Product> optionalProduct=productRepository.findById(id);
        if(optionalProduct.isPresent())
        {
            photosRepository.deleteById(id);
            productRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.FOUND).body(optionalProduct.get());
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/products/photos/{id}")
    public ResponseEntity<Photos> getProductPhotoByProductId(@PathVariable long id)
    {
        Optional<Photos> optionalPhotos=photosRepository.getByProductId(id);
        if(optionalPhotos.isPresent())
        {
            return ResponseEntity.status(HttpStatus.FOUND).body(optionalPhotos.get());
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
