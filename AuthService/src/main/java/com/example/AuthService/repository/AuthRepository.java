package com.example.AuthService.repository;

import com.example.AuthService.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends MongoRepository<User,String> {
    Optional<User> findByUsername(String username);
}
