package com.example.ecommerce.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY =
            "mySecretKeyForJwtAuthentication123456789";

    private static final long EXPIRATION_TIME =
            1000L * 60 * 60; // 1 hour

    private static SecretKey getKey() {
        return Keys.hmacShaKeyFor(
                SECRET_KEY.getBytes(StandardCharsets.UTF_8)
        );
    }

    // 1. Generate JWT token after successful login
    public static String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(
                        new Date(System.currentTimeMillis()
                                + EXPIRATION_TIME)
                )
                .signWith(getKey())
                .compact();
    }

    // 2. Extract username from JWT
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // 3. Validate JWT and check the username
    public boolean isTokenValid(
            String token,
            UserDetails userDetails) {

        final String username = extractUsername(token);

        return username.equals(userDetails.getUsername())
                && extractAllClaims(token)
                .getExpiration()
                .after(new Date());
    }

    // 4. Parse claims and verify the JWT signature
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}