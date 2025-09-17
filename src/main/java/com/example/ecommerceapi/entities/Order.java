package com.example.ecommerceapi.entities;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="orders")
public class Order
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "cartId" , referencedColumnName = "id",nullable = false)
    private Cart cartId;

    @ManyToOne
    @JoinColumn(name="user_id" , referencedColumnName = "id")
    private User userId;

    @Column(name="order_total")
    private Double orderTotal; // = carttotal

    @ManyToOne
    @JoinColumn(name="adress_id", referencedColumnName = "id")
    private Adress adressId;

    @Column(name="order_date")
    private LocalDateTime createdAt;

    @Column(name="updated_date")
    private LocalDateTime updatedAt;

    @Column(name="Status")
    private OrderStatus status;

    public Order() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    // getters setters

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    public OrderStatus getStatus() {
        return status;
    }
    public Double getOrderTotal() {
        return orderTotal;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    public Cart getCartId() {
        return cartId;
    }
    public Adress getAdressId() {
        return adressId;
    }
    public  void setAdressId(Adress adressId) {
        this.adressId = adressId;
    }
    public void setCartId(Cart cartId) {
        this.cartId = cartId;
    }
    public User getUserId() {
        return userId;
    }
    public void setUserId(User userId) {
        this.userId = userId;
    }
    public void setOrderTotal(Double orderTotal) {
        this.orderTotal = orderTotal;

    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;

    }


}
