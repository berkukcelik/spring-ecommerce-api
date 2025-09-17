package com.example.ecommerceapi.repository;

import com.example.ecommerceapi.entities.Product;
import com.example.ecommerceapi.entities.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product getProductByproductName(String productName);



}
