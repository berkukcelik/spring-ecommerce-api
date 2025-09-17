package com.example.ecommerceapi.Controller;

import com.example.ecommerceapi.Service.OrderService;
import com.example.ecommerceapi.entities.Order;
import com.example.ecommerceapi.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@AuthenticationPrincipal User user , @RequestParam Long adressId){
        return ResponseEntity.ok(orderService.createOrder(user,adressId));
    }

}