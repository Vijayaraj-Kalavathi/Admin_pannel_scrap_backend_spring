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

    private static final String TOKEN_TYPE_CLAIM = "token_type";
    private static final String ACCESS_TOKEN_TYPE = "access";
    private static final String REFRESH_TOKEN_TYPE = "refresh";
    private static final String SECRET_KEY =
            "mySecretKeyForJwtAuthentication123456789";

    private static final long ACCESS_TOKEN_EXPIRATION_TIME =
            1000L * 60 * 3; // 15 minutes
    private static final long REFRESH_TOKEN_EXPIRATION_TIME =
            1000L * 60 * 60 * 24 * 7;

    private static SecretKey getKey() {
        return Keys.hmacShaKeyFor(
                SECRET_KEY.getBytes(StandardCharsets.UTF_8)
        );
    }

    public String generateAccessToken(String username) {
        return generateToken(
                username,
                ACCESS_TOKEN_TYPE,
                ACCESS_TOKEN_EXPIRATION_TIME
        );
    }

    public String generateRefreshToken(String username) {
        return generateToken(username, REFRESH_TOKEN_TYPE, REFRESH_TOKEN_EXPIRATION_TIME);
    }

    private String generateToken(
            String username,
            String tokenType,
            long expirationTime) {
        return Jwts.builder()
                .subject(username)
                .claim(TOKEN_TYPE_CLAIM, tokenType)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getKey())
                .compact();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public boolean isTokenValid(
            String token,
            UserDetails userDetails) {

        Claims claims = extractAllClaims(token);

        String tokenType = claims.get(TOKEN_TYPE_CLAIM, String.class);
        return (tokenType == null || ACCESS_TOKEN_TYPE.equals(tokenType))
                && userDetails.getUsername().equals(claims.getSubject())
                && claims.getExpiration().after(new Date());
    }

    public boolean isRefreshToken(String token) {
        Claims claims = extractAllClaims(token);
        return REFRESH_TOKEN_TYPE.equals(claims.get(TOKEN_TYPE_CLAIM, String.class))
                && claims.getExpiration().after(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}