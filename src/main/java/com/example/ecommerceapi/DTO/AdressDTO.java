package com.example.ecommerceapi.DTO;

import jakarta.validation.constraints.NotBlank;

public class AdressDTO {
    @NotBlank
    private String adressTitle;
    @NotBlank
    private String city;
    @NotBlank
    private String town;
    @NotBlank
    private String street;
    @NotBlank
    private String adressDetail;
    public AdressDTO(String adressTitle, String city, String town, String street, String adressDetail) {
        this.adressTitle = adressTitle;
        this.city = city;
        this.town = town;
        this.street = street;
        this.adressDetail = adressDetail;

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