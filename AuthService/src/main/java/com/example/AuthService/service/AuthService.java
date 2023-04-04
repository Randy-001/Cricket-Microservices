package com.example.AuthService.service;

import com.example.AuthService.dto.AuthRequest;
import com.example.AuthService.dto.AuthResponse;
import com.example.AuthService.dto.ValidResponse;
import com.example.AuthService.models.User;
import com.example.AuthService.repository.AuthRepository;
import com.example.AuthService.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthService {

    private AuthRepository authRepository;
    private AuthenticationManager authenticationManager;
    private MyUserDetailsService myUserDetailsService;
    private JwtUtil jwtUtil;
    @Autowired
    public AuthService(AuthRepository authRepository, AuthenticationManager authenticationManager, MyUserDetailsService myUserDetailsService, JwtUtil jwtUtil) {
        this.authRepository = authRepository;
        this.authenticationManager=authenticationManager;
        this.myUserDetailsService = myUserDetailsService;
        this.jwtUtil=jwtUtil;
    }

    public HashMap<String, Boolean> add(User user) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        HashMap<String,Boolean> status= new HashMap<>();
        user.setPassword(encoder.encode(user.getPassword()));
        try {
            this.authRepository.save(user);
            status.put("success",true);

        } catch (Exception c) {
            status.put("success",false);

        }
        return  status;

    }

    public AuthResponse login(AuthRequest authRequest) throws Exception{
        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword())
            );

        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        final UserDetails userDetails = myUserDetailsService
                .loadUserByUsername(authRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        return new AuthResponse(jwt);

    }


}
