package com.example.ecommerceapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Table(name="cart")
public class Cart {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @OneToMany(mappedBy = "cartId" , fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    // sonsuz döngü olmaması icin
    @JsonIgnore
    private List<CartItem> cartItems;

    @OneToOne
    @JoinColumn(referencedColumnName = "id" , name = "user_id",nullable = false)
    @JsonIgnore
    private User userId;

    @Column(name="cart_total")
    private Double cartTotal ;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "cartId")
    @JsonIgnore
    private Order order;


    public Cart() {
    }
    public Cart(User userId){
        this.userId = userId;
        cartItems = new ArrayList<>();
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        cartTotal = 0.00;
    }

    // getters setters metotlar

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public User getUserId() {
        return userId;
    }
    public void setUserId(User userId) {
        this.userId = userId;
    }
    public Double getCartTotal() {
        return cartTotal;
    }
    public void setCartTotal(Double cartTotal) {
        this.cartTotal = cartTotal;
    }
    public List<CartItem> getItems(){
        return cartItems;
    }
    public void setItems(List<CartItem> items){
        this.cartItems = items;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }



}