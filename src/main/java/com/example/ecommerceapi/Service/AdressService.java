package com.example.ecommerceapi.Service;

import com.example.ecommerceapi.entities.Adress;
import com.example.ecommerceapi.entities.User;
import com.example.ecommerceapi.errorHandling.EntityNotFoundException;
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
    public Adress getAdressById(Long adressId) {
        return adressRepository.findById(adressId).get();
    }
    public Adress updateAdress(Long adressId, Adress adress, User user) {
        if(adress.getUserId().getId() == user.getId()) {
            var updatedAdress = getAdressById(adressId);
            updatedAdress.setStreet(adress.getStreet());
            updatedAdress.setCity(adress.getCity());
            updatedAdress.setTown(adress.getTown());
            updatedAdress.setAdressDetail(adress.getAdressDetail());
            return adressRepository.save(updatedAdress);
        }
        else throw new EntityNotFoundException("Adress not found");
    }
}