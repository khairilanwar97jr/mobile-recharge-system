package com.kai.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    private String secret = "mySuperSecretKeyForJwtAuthentication2026VerySecureKey123456";

    private long expiration = 1000 * 60 * 60; // 1 hour


    public String generateToken(String email) {

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + expiration)
                )
                .signWith(
                        SignatureAlgorithm.HS256,
                        secret
                )
                .compact();
    }


    public String extractEmail(String token) {

        Claims claims = extractClaims(token);

        return claims.getSubject();
    }


    public boolean validateToken(String token) {

        try {

            extractClaims(token);

            return true;

        } catch (Exception e) {

            return false;
        }
    }


    private Claims extractClaims(String token) {

        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}