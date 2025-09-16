package com.example.ecommerceapi.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="category_name",nullable=false)
    private String categoryName;

    @ManyToMany
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Product> products;

    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name="category_tags")
    private Set<String> tags;

    public Category(String categoryName , Set<String> tags) {
        products = new ArrayList<Product>();
        this.tags = tags ;
        this.categoryName = categoryName;
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    public Category() {}
    //getters setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public List<Product> getProducts() {
        return products;

    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
    public Set<String> getTags() {
        return tags;
    }
    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public  LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public  LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}