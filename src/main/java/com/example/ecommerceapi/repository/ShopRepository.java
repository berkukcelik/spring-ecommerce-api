package com.example.ecommerceapi.repository;

import com.example.ecommerceapi.entities.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop,Long> {
    Shop getShopByshopName(String shopName);

}