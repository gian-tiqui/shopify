package com.gian.shopifyAPI.controller;

import com.gian.shopifyAPI.dto.ReqRes;
import com.gian.shopifyAPI.entity.Product;
import com.gian.shopifyAPI.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminUsers {

    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/public/product")
    public ResponseEntity<Object> getProducts() {
        return ResponseEntity.ok(productRepo.findAll());
    }

    @PostMapping("/admin/createproduct")
    public ResponseEntity<Object> createProduct(@RequestBody ReqRes product) {

        Product savedProduct = new Product();

        savedProduct.setName(product.getName());

        return ResponseEntity.ok(productRepo.save(savedProduct));
    }

    @GetMapping("/user/alone")
    public ResponseEntity<Object> userAlone() {
        return ResponseEntity.ok("User api");
    }

    @GetMapping("/adminuser/both")
    public ResponseEntity<Object> adminAndUsersEndpoint() {
        return ResponseEntity.ok("User and Admin api");
    }

    @GetMapping("/gian/meow")
    public ResponseEntity<Object> gian() {
        return ResponseEntity.ok("meow");
    }
}
