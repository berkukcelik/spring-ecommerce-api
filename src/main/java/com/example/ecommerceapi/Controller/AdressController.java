package com.example.ecommerceapi.Controller;

import com.example.ecommerceapi.DTO.AdressDTO;
import com.example.ecommerceapi.Service.AdressService;
import com.example.ecommerceapi.entities.Adress;
import com.example.ecommerceapi.entities.User;
import com.example.ecommerceapi.repository.AdressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adress")
public class AdressController {
    @Autowired
    private AdressService addressService;

    @PostMapping
    public ResponseEntity<Adress> createAdress(@RequestBody AdressDTO adressDTO,
                                               @AuthenticationPrincipal User user) {
        Adress adress = new Adress(
                adressDTO.getAdressTitle(),
                adressDTO.getCity(),
                adressDTO.getTown(),
                adressDTO.getStreet(),
                adressDTO.getAdressDetail()
        );
        return new ResponseEntity<>(addressService.saveAdressForUser(adress, user), HttpStatus.CREATED);
    }
    @GetMapping("/my")
    public ResponseEntity<List<Adress>> getAllAdress(@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(addressService.getAdressesByUserId(user.getId()), HttpStatus.OK);
    }
}