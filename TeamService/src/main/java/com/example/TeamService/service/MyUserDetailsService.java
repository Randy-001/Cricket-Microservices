package com.example.TeamService.service;


import com.example.TeamService.dto.MyUserDetails;
import com.example.TeamService.models.User;
import com.example.TeamService.repository.AuthRepository;
import com.example.TeamService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Username ;  "+username);
        Optional<User> user = this.userRepository.findByUsername(username);
        user.orElseThrow(()-> new UsernameNotFoundException("Username Notfound"+username));
        return user.map(MyUserDetails::new).get();

    }
}
