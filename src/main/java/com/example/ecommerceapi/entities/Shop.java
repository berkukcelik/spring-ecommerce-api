package com.example.ecommerceapi.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="shop")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="shop_id")
    private Long id;

    @OneToMany(mappedBy = "shop" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private List<Product> products ;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    public Shop() {
        products = new ArrayList<Product>();
        createdAt = LocalDateTime.now();
    }


    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }
    public List<Product> getProductId()
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

}