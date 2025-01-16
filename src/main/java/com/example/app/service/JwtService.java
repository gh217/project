package com.example.app.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;

public interface JwtService {

    public String extractUserName(String token);
    public String generateToken(UserDetails userDetails);
    public boolean isTokenValid(String token , UserDetails userDetails);
    String generateRefreshToken(HashMap<String, Object> extracClaim, UserDetails usersDetails);
}
