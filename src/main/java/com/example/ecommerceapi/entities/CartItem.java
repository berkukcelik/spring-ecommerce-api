package com.example.ecommerceapi.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="cartItem")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="cart_id",referencedColumnName ="id",nullable = false )
    @JsonIgnore
    private Cart cartId;

    @Column(name="quantity")
    private int quantity ;

    @Column(name="item_total")
    private Double itemTotal;

    @ManyToOne
    @JoinColumn(name="product_id" , referencedColumnName = "id")
    private Product productId;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    public CartItem(Cart cartId, int quantity , Product productId) {
        this.cartId = cartId;
        this.quantity = quantity;
        this.productId = productId;
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        itemTotal = quantity * productId.getPrice();
        this.quantity = quantity;
    }
    public CartItem() {}


    // getters setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Cart getCartId() {
        return cartId;
    }
    public void setCartId(Cart cartId) {
        this.cartId = cartId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Product getProductId(){
        return productId;
    }
    public void setProductId(Product productId){
        this.productId = productId;
    }

    public void setItemTotal(Double itemTotal) {
        this.itemTotal = itemTotal;
    }
    public Double getItemTotal() {
        return itemTotal;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }


}