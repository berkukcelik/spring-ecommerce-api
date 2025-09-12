package com.example.ecommerceapi.entities;

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
    private Long id;

    @Column(name="category_name")
    private String categoryName;

    @ManyToMany(mappedBy = "category")
    private List<Product> products;

    private Set<String> tags;

    public Category() {
        products = new ArrayList<Product>();
        tags = new HashSet<String>();
    }

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



}