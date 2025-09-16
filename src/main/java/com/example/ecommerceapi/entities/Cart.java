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
    private User userId;

    @Column(name="cart_total")
    private BigDecimal cartTotal = new BigDecimal(0.00);

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "cartId")
    private Order order;

    public Cart() {
        cartItems = new ArrayList<>();
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
    public BigDecimal getCartTotal() {
        return cartTotal;
    }
    public void setCartTotal(BigDecimal cartTotal) {
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


}