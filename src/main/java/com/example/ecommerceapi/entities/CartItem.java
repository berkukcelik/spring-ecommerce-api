package com.example.ecommerceapi.entities;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="cartItem")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="cart_id",referencedColumnName ="id",nullable = false)
    private Cart cartId;

    @Column(name="quantity")
    private int quantity = 0;

    @Column(name="item_total")
    private BigDecimal itemTotal =  BigDecimal.ZERO;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product_id" , referencedColumnName = "id",nullable = false)
    private Product productId ;

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

    public void setItemTotal(BigDecimal itemTotal) {
        this.itemTotal = itemTotal;
    }
    public BigDecimal getItemTotal() {
        return itemTotal;
    }



}