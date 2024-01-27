package com.inventory.management.MyInventory.controllers;

import com.inventory.management.MyInventory.pojo.Product;
import com.inventory.management.MyInventory.pojo.Sale;
import com.inventory.management.MyInventory.repositories.ProductRepository;
import com.inventory.management.MyInventory.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@RestController
public class SaleController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SaleRepository saleRepository;
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/sales")
    public Iterable<Sale> getAllSales()
    {
        return saleRepository.findAll();
    }
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/sales")
    public ResponseEntity<Sale> addSale(@RequestBody Sale sale)
    {
        Optional<Product> optionalProduct=productRepository.findById(sale.getProductId());
        if(optionalProduct.isPresent())
        {
            Product presentProduct=optionalProduct.get();
            long quantity=presentProduct.getQuantity();
            long soldStock=sale.getQuantitySold();
            long remaningStock=quantity-soldStock;
            presentProduct.setQuantity(remaningStock);
            productRepository.save(presentProduct);
            BigDecimal price=sale.getTotalAmmount();
            BigDecimal totalPrice=price.multiply(BigDecimal.valueOf(soldStock));
            sale.setProductId(presentProduct.getProductId());
            sale.setSaleDate(new Date(System.currentTimeMillis()));
            sale.setQuantitySold(soldStock);
            sale.setTotalAmmount(totalPrice);
            saleRepository.save(sale);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(sale);
    }

}
