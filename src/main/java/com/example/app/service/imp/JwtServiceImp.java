package com.example.app.service.imp;


import com.example.app.entity.Users;
import com.example.app.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JwtServiceImp implements JwtService {


    private final String KEY="456sf4g654s6h4456sf4g654s6h44fghf456sf4g654s6h44fghf456sf4g654s6h44fghf456sf4g654s6h44fghf456sf4g654s6h44fghf456sf4g654s6h44fghf4fghf";

    public String generateToken(UserDetails userDetails) {
        return  Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                .signWith(getSighnedKey(), SignatureAlgorithm.HS512)
                .compact();
    }


    public String getUserNameFromJwtToken(String token) {
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
        byte[] key = Decoders.BASE64.decode(KEY);
        return Keys.hmacShaKeyFor(key);
    }

    public boolean isTokenValid(String token , UserDetails userDetails){
        String username = getUserNameFromJwtToken(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }

    @Override
    public String generateRefreshToken(HashMap<String, Object> extracClaim, UserDetails usersDetails) {
        return  Jwts.builder().setClaims(extracClaim).setSubject(usersDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24*7))
                .signWith(getSighnedKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token,Claims::getExpiration).before(new Date());
    }

}
