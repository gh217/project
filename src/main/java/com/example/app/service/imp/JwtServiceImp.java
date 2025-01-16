package com.example.app.service.imp;


import com.example.app.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtServiceImp implements JwtService {

    private final String KEY_SEC="wZqLw8uJv9XkR1L8pTgJmN3nY6FbD2cZsQxR9mP7tLkXv9WqLw8uJv9XkR1L8pTgJmN3nY6FbD2cZsQxR9mP7tLkXv9W==";
    public String generateToken(HashMap<String, Object> extracClaim, UserDetails usersDetails) {

        return  Jwts.builder()
                .setClaims(extracClaim)
                .setSubject(usersDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24*7))
                .signWith(getSighnedKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public String extractUserName(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims,T> claimsTFunction) {
        final Claims claims=extractAllClaim(token);
        return claimsTFunction.apply(claims);
    }

    private Claims extractAllClaim(String token) {
        return Jwts.parserBuilder().setSigningKey(getSighnedKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSighnedKey() {
        byte[] keyBytes = Decoders.BASE64.decode(KEY_SEC);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenValid(String token , UserDetails userDetails){
        String username = extractUserName(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }


    private boolean isTokenExpired(String token) {
        return extractClaims(token,Claims::getExpiration).before(new Date());
    }


}
