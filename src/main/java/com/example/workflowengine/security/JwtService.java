package com.example.workflowengine.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET =
            "workflowengineworkflowengineworkflowengine1234567890";

    private static final long ACCESS_TOKEN_EXP = 1000L * 60 * 15;
    private static final long REFRESH_TOKEN_EXP = 1000L * 60 * 60 * 24 * 7;

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(String email) {

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXP))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(String email) {

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXP))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token) {

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            extractEmail(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}