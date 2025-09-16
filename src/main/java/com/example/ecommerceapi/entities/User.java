package com.example.ecommerceapi.entities;

import com.example.ecommerceapi.entities.Order;

import com.fasterxml.jackson.annotation.JsonIgnore;
// JPA - JakartaEE standard API for object relational mapping
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "`user`")
public class User implements UserDetails {
    // marks a field as primary key
    @Id
    // for auto increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="user_name",unique = true , nullable = false , length = 32)
    private String userName;

    @Column(name="user_email", unique = true , length = 50,nullable = false)
    private String userEmail;

    @Column(name="hashed_password" , nullable = false )
    private String hashedPassword;

    @Column(name="phone_number" , unique = true,nullable=false)
    private String phoneNumber;

    /* org.hibernate -> fetch
    fetch = fetchType.LAZY  -> biz veritabanından user'ları çektiğimiz zaman sadece userları çekecek bağlı oldugu orderları çekmeyecek
    fetch = FetchType.EAGER -> hem user hem de orderları cekecek .
     CascadeType.ALL bir user silinince bağlı olan tüm order entityleri de silinecek .
    */
    @OneToMany(mappedBy = "userId" , cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

    // mapped by -> karşı entitydeki yöneten property yani foreign keyi tutan .
    @OneToMany(mappedBy = "userId", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JsonIgnore
    private Set<Adress> adresses = new HashSet<>();

    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    // column olarak eklemek ve foreign key vermek icin
    @JoinColumn(name = "usercart_id" ,referencedColumnName = "id")
     private Cart userCartId;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="role")
    private Roles role;

    @Column(name="updated_at" , nullable = true)
    private LocalDateTime updatedAt;

    protected User() {

    }


     public User(String userName,
                 String userEmail,
                 String hashedPassword,
                 String phoneNumber

                  ) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.hashedPassword = hashedPassword;
        this.phoneNumber = phoneNumber;
        createdAt =LocalDateTime.now();


    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public String getPassword() {
        return hashedPassword;
    }
    // unique özellik
    public String getUsername() {
        return userEmail;
    }




    // getters setters metotlari
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public String getHashedPassword() {
        return hashedPassword;
    }
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Order> getOrders() {
        return orders;
    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Roles getRole() {
        return role;
    }
    public void setRole(Roles role) {
        this.role = role;
    }

    public Set<Adress> getAdresses() {
        return adresses;
    }
    public void setAdresses(Set<Adress> adresses) {
        this.adresses = adresses;
    }





}
