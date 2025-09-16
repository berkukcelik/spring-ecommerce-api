package com.example.ecommerceapi.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class AuthRequest {
    @NotBlank(message = "Email bos olamaz")
    @Email
    private String email;
    @NotBlank(message="sifre bos olamaz")
    private String password;

    // getters setters
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

}