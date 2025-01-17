package com.example.app.security;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;

public interface JwtService {

    public String extractUserName(String token);
    public String generateToken(HashMap<String, Object> extracClaim, UserDetails usersDetails);
    public boolean isTokenValid(String token , UserDetails userDetails);
}
