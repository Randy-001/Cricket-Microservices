package com.example.AuthService.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class ValidResponse {

    private String username;
    private String roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
