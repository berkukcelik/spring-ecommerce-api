package com.example.ecommerceapi.Controller;

import com.example.ecommerceapi.Service.CartService;
import com.example.ecommerceapi.entities.Cart;
import com.example.ecommerceapi.entities.CartItem;
import com.example.ecommerceapi.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;


    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@AuthenticationPrincipal User user,
                                          @RequestParam Long productId,
                                          @RequestParam int quantity) {
        Cart updatedCart = cartService.addProductToCart(user, productId, quantity);
        return ResponseEntity.ok(updatedCart);
    }


    @PutMapping("/update-item")
    public ResponseEntity<Cart> updateCartItem(@RequestParam Long cartItemId,
                                               @RequestParam int quantity) {
        Cart updatedCart = cartService.updateCartItemQuantity(cartItemId, quantity);
        return ResponseEntity.ok(updatedCart);
    }


    @DeleteMapping("/remove-item")
    public ResponseEntity<Cart> removeCartItem(@RequestParam Long cartItemId) {
        Cart updatedCart = cartService.removeCartItem(cartItemId);
        return ResponseEntity.ok(updatedCart);
    }


    @DeleteMapping("/clear/my")
    public ResponseEntity<Cart> clearCart(@AuthenticationPrincipal User user) {
        Cart clearedCart = cartService.clearCart(user);
        return ResponseEntity.ok(clearedCart);
    }


    @GetMapping("/my/items")
    public ResponseEntity<List<CartItem>> getCartItems(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(cartService.getCartItems(user));
    }


    @GetMapping("/my")
    public ResponseEntity<Cart> getCart(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(cartService.getCart(user));
    }
}
