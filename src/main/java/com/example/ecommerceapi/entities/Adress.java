package com.example.ecommerceapi.entities;

import jakarta.persistence.*;

@Entity
@Table(name="adress")
public class Adress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id" , referencedColumnName = "id")
    private User userId;


    @Column(name="adress_title",nullable = false)
    private String adressTitle;

    @Column(name="city" , nullable = false)
    private String city;

    @Column(name="town",nullable = false)
    private String town;

    @Column(name="street")
    private String street;


    @Column(name="adress_detail",nullable = false)
    private String adressDetail;

    public Adress(
            String adressTitle, String city, String town, String street, String adressDetail
    ) {
        this.adressTitle = adressTitle;
        this.city = city;
        this.town = town;
        this.street = street;
        this.adressDetail = adressDetail;

    }
    public Adress() {}

    // getters setters
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
    public String getAdressTitle() {
        return adressTitle;
    }
    public void setAdressTitle(String adressTitle) {
        this.adressTitle = adressTitle;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getTown() {
        return town;
    }
    public void setTown(String town) {
        this.town = town;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getAdressDetail() {
        return adressDetail;
    }
    public void setAdressDetail(String adressDetail) {
        this.adressDetail = adressDetail;
    }






}