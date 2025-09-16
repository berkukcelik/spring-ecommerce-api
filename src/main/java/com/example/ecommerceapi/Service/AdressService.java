package com.example.ecommerceapi.Service;

import com.example.ecommerceapi.entities.Adress;
import com.example.ecommerceapi.entities.User;
import com.example.ecommerceapi.repository.AdressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdressService {
    @Autowired
    private AdressRepository adressRepository;

    public Adress saveAdressForUser(Adress adress, User user) {
        adress.setUserId(user);
        return adressRepository.save(adress);

    }
    public List<Adress> getAdressesByUserId(Long userId) {
        return adressRepository.findByUserId(userId);
    }
}