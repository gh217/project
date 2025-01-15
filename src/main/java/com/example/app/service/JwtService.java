package com.example.app.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    public String getUserNameFromJwtToken(String token);
    public String generateToken(UserDetails userDetails);
    public boolean isTokenValid(String token , UserDetails userDetails);

}
