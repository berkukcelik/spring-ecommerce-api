package com.example.ecommerceapi.entities;

import jakarta.persistence.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="price", nullable = false )
    private Double price;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name="product_name",nullable = false, unique = true)
    private String productName;

    @Column(name="product_description" ,nullable = false)
    private String productDescription;

    @ManyToOne
    @JoinColumn(name="shop_id",referencedColumnName = "id",nullable = false)
    private Shop shopId;

    @ManyToMany(mappedBy = "products",fetch = FetchType.EAGER)
    private List<Category> categories;

    @OneToMany(mappedBy = "productId")
    private List<CartItem> cartItems;


    @Column(name="quantity")
    private Integer quantity;

    public Product(Double price , String productName , String productDescription , Shop shopId, List<Category> categories, Integer quantity) {
        this.price = price;
        this.productName = productName;
        this.productDescription = productDescription;
        this.shopId = shopId ;
        this.createdAt = LocalDateTime.now();
        this.quantity = quantity;
        this.categories = categories;
    }
    protected Product() {}

    // getters setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
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
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductDescription() {
        return productDescription;
    }
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Shop getShopId() {
        return shopId;
    }
    public void setShopId(Shop shopId) {
        this.shopId = shopId;
    }
    public  List<Category> getCategories() {
        return categories;
    }
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    public  Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}