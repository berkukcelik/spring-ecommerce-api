// cevap olarak tokeni vermemiz lazim
package com.example.ecommerceapi.DTO;

public class AuthResponse {
    private String token ;

    private String message ;

    public AuthResponse(String token,String message) {
        this.token = token;
        this.message = message;
    }
    public String getToken() {
        return token;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }


    
}