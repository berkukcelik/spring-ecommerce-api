package com.example.ecommerceapi.Service;

import com.example.ecommerceapi.entities.Adress;
import com.example.ecommerceapi.entities.User;
import com.example.ecommerceapi.repository.UserRepository;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) throws InvalidPropertyException
    {
        return userRepository.save(user);
    }

}