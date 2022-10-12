package com.revature.services;

import org.springframework.stereotype.Service;

import com.revature.entity.UserEntity;

import java.net.URI;
import java.util.Optional;

@Service
public class AuthService {

    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public Optional<UserEntity> findByCredentials(String email, String password) {
        return userService.findByCredentials(email, password);
    }

    public Optional<UserEntity> findByName(String firstName, String lastName) {
        return userService.findByName(firstName, lastName);
    }
    
    public UserEntity register(UserEntity user) {
        return userService.save(user);
    }
}
