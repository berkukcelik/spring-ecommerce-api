package com.example.ecommerceapi.repository;
import com.example.ecommerceapi.entities.Roles;
import com.example.ecommerceapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByuserName(String userName);

    Optional<User> findByuserEmail(String userEmail);
}