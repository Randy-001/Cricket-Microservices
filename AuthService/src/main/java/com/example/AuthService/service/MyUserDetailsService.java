package com.example.AuthService.service;

import com.example.AuthService.dto.MyUserDetails;
import com.example.AuthService.models.User;
import com.example.AuthService.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private AuthRepository authRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Username ;  "+username);
        Optional<User> user = this.authRepository.findByUsername(username);
        user.orElseThrow(()-> new UsernameNotFoundException("Username Notfound"+username));
        return user.map(MyUserDetails::new).get();

    }
}
