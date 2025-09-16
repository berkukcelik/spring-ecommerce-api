package com.example.ecommerceapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="shop")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="shop_name" , nullable=false , unique=true)
    private String shopName;

    @OneToMany(mappedBy = "shopId" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Product> products ;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Shop(String shopName) {
        products = new ArrayList<Product>();
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        this.shopName = shopName;
    }
    public Shop() {

    }


    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }
    public List<Product> getProducts()
    {
        return products;
    }
    public void setProducts(List<Product> products){
        this.products = products;
    }
    public LocalDateTime getCreatedAt()
    {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt)
    {
        this.createdAt = createdAt;
    }
    public void setShopName(String shopName)
    {
        this.shopName = shopName;
    }
    public String getShopName()
    {
        return shopName;
    }
    public LocalDateTime getUpdatedAt()
    {
        return updatedAt;

    }
    public void setUpdatedAt(LocalDateTime updatedAt)
    {
        this.updatedAt = updatedAt;

    }
}