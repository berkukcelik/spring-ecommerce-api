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
    private Long id;

    @OneToOne(mappedBy = "orders")
    @JoinColumn(name = "cartId" , referencedColumnName = "id",nullable = false)
    private Cart cartId;


    @Column(name="order_total")
    private BigDecimal orderTotal = cartId.getCartTotal();

    @ManyToOne
    @JoinColumn(name="adress_id", referencedColumnName = "id" , nullable = false)
    private Adress adressId;

    @Column(name="order_date")
    private LocalDateTime createdAt;

    @Column(name="updated_date")
    private LocalDateTime updatedAt;

    @Column(name="Status")
    private OrderStatus status;

    public Order() { }

    // getters setters

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    public OrderStatus getStatus() {
        return status;
    }
    public BigDecimal getOrderTotal() {
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

}
