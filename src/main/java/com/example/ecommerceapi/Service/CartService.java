package com.example.ecommerceapi.Service;

import com.example.ecommerceapi.entities.Cart;
import com.example.ecommerceapi.entities.CartItem;
import com.example.ecommerceapi.entities.Product;
import com.example.ecommerceapi.entities.User;
import com.example.ecommerceapi.errorHandling.EntityNotFoundException;
import com.example.ecommerceapi.repository.CartItemRepository;
import com.example.ecommerceapi.repository.CartRepository;
import com.example.ecommerceapi.repository.ProductRepository;
import com.example.ecommerceapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Cart addProductToCart(User user, Long productId, int quantity) {


        Cart cart = user.getCartId();
        if (cart == null) {
            cart = new Cart(user);
            cart = cartRepository.save(cart);
            user.setCartId(cart);
            userRepository.save(user);
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        CartItem cartItem = new CartItem(cart, quantity, product);
        cartItemRepository.save(cartItem);

        cart.getItems().add(cartItem);
        cart.setCartTotal(cart.getCartTotal() + cartItem.getItemTotal());
        cart.setUpdatedAt(LocalDateTime.now());

        return cartRepository.save(cart);
    }
    @Transactional
    public Cart updateCartItemQuantity(Long cartItemId, int newQuantity) {
        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException("CartItem not found"));

        Double oldTotal = item.getItemTotal();
        item.setQuantity(newQuantity);
        item.setItemTotal(item.getProductId().getPrice() * newQuantity);
        item.setUpdatedAt(LocalDateTime.now());
        cartItemRepository.save(item);

        Cart cart = item.getCartId();
        cart.setCartTotal(cart.getCartTotal() - oldTotal + item.getItemTotal());
        cart.setUpdatedAt(LocalDateTime.now());

        return cartRepository.save(cart);
    }

    @Transactional
    public Cart removeCartItem(Long cartItemId) {
        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException("CartItem not found"));

        Cart cart = item.getCartId();
        cart.setCartTotal(cart.getCartTotal() - item.getItemTotal());
        cart.getItems().remove(item);
        cartItemRepository.delete(item);
        cart.setUpdatedAt(LocalDateTime.now());

        return cartRepository.save(cart);
    }

    @Transactional
    public Cart clearCart(User user) {

        Cart cart = user.getCartId();
        if (cart == null) return null;

        cart.getItems().clear();
        cartItemRepository.deleteAll(cart.getItems());
        cart.setCartTotal(0.0);
        cart.setUpdatedAt(LocalDateTime.now());

        return cartRepository.save(cart);
    }



    public List<CartItem> getCartItems(User user) {
        Cart cart = user.getCartId();
        if (cart == null) {
            return new ArrayList<>();
        }
        return cart.getItems();
    }

    public Cart getCart(User user) {
        return user.getCartId();
    }
}
