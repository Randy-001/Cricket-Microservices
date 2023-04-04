package com.example.AuthService.controller;

import com.example.AuthService.dto.AuthRequest;
import com.example.AuthService.dto.AuthResponse;
import com.example.AuthService.dto.ValidResponse;
import com.example.AuthService.models.User;
import com.example.AuthService.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/subs")
    public HashMap<String,Boolean> addUser(@RequestBody User user)
    {
            return this.authService.add(user);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) throws Exception
    {

        return this.authService.login(authRequest);

    }

}
