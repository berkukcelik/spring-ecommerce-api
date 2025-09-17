package com.example.ecommerceapi.repository;

import com.example.ecommerceapi.entities.Cart;
import com.example.ecommerceapi.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

}