package com.example.app.security;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    public UserDetailsService userDetailsService();
}
