package com.revature.controllers;

import com.revature.dtos.LoginRequest;
import com.revature.dtos.RegisterRequest;
import com.revature.entity.UserEntity;
import com.revature.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserEntity> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        Optional<UserEntity> optional = authService.findByCredentials(loginRequest.getEmail(), loginRequest.getPassword());

        if(!optional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        session.setAttribute("user", optional.get());

        return ResponseEntity.ok(optional.get());
    }
    
    @PostMapping("/fetch")
    public ResponseEntity<UserEntity> fetch(@RequestBody String firstName, String lastName) 
    {
        Optional<UserEntity> optional = authService.findByName(firstName, lastName);

        if(!optional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(optional.get());
    }
    
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.removeAttribute("user");

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity> register(@RequestBody RegisterRequest registerRequest) {
        UserEntity created = new UserEntity(0,
                registerRequest.getEmail(),
                registerRequest.getPassword(),
                registerRequest.getFirstName(),
                registerRequest.getLastName(),
        		registerRequest.getBio());

        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(created));
    }
}
