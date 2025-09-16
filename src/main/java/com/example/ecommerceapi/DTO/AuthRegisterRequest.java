package com.example.ecommerceapi.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AuthRegisterRequest {

    @NotBlank(message = "Email bos olamaz")
    @Email
    private String email;
    @NotBlank(message = "Sifre bos olamaz")
    private String password;
    @NotBlank(message = "Isim bos olamaz")
    private String userName;
    @NotBlank(message = "Telefon numarasi bos olamaz")
    private String phoneNumber;

    public AuthRegisterRequest(String email, String password, String userName, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
    }
    protected AuthRegisterRequest() {}

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}