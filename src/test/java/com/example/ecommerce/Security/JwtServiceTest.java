package com.example.ecommerce.Security;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JwtServiceTest {

    private final JwtService jwtService = new JwtService();

    @Test
    void accessTokenIsValidForAuthenticationButRefreshTokenIsNot() {
        UserDetails user = User.withUsername("test-user")
                .password("password")
                .authorities("ROLE_USER")
                .build();
        String accessToken = jwtService.generateAccessToken(user.getUsername());
        String refreshToken = jwtService.generateRefreshToken(user.getUsername());

        assertTrue(jwtService.isTokenValid(accessToken, user));
        assertFalse(jwtService.isTokenValid(refreshToken, user));
        assertEquals(user.getUsername(), jwtService.extractUsername(refreshToken));
        assertTrue(jwtService.isRefreshToken(refreshToken));
        assertFalse(jwtService.isRefreshToken(accessToken));
    }
}
