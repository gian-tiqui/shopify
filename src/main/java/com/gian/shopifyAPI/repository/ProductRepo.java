package com.gian.shopifyAPI.repository;

import com.gian.shopifyAPI.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}
