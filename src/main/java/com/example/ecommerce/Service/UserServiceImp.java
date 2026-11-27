package com.example.ecommerce.Service;

import com.example.ecommerce.Model.User;
import com.example.ecommerce.Model.AuthTokens;
import com.example.ecommerce.Repository.UserRepository;
import com.example.ecommerce.Security.JwtService;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserServiceImp(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder, JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public User register(User user) {

        // Check username already exists
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        // Never trust the role supplied by the client
        user.setRole("USER");

        // Encrypt password
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        // Save user
        return userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {

        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );
    }

    @Override
    public User registerAdmin(User user) {

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("AdminUsername already exists");
        }

        // Assign ADMIN explicitly
        user.setRole("ADMIN");

        // Encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }



    @Override
    public AuthTokens login(String username, String password) {

        // 1. Find user
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Invalid username or password"
                        )
                );

        // 2. Check password
        boolean passwordMatch =
                passwordEncoder.matches(
                        password,
                        user.getPassword()
                );

        if (!passwordMatch) {
            throw new RuntimeException(
                    "Invalid username or password"
            );
        }

        return new AuthTokens(
                jwtService.generateAccessToken(user.getUsername()),
                jwtService.generateRefreshToken(user.getUsername())
        );
    }

    @Override
    public AuthTokens refresh(String refreshToken) {
        if (refreshToken == null || refreshToken.isBlank()) {
            throw invalidRefreshToken();
        }

        try {
            if (!jwtService.isRefreshToken(refreshToken)) {
                throw invalidRefreshToken();
            }

            String username = jwtService.extractUsername(refreshToken);
            User user = userRepository.findByUsername(username)
                    .orElseThrow(UserServiceImp::invalidRefreshToken);

            return new AuthTokens(
                    jwtService.generateAccessToken(user.getUsername()),
                    refreshToken
            );
        } catch (JwtException | IllegalArgumentException exception) {
            throw invalidRefreshToken();
        }
    }

    private static ResponseStatusException invalidRefreshToken() {
        return new ResponseStatusException(
                HttpStatus.UNAUTHORIZED,
                "Invalid or expired refresh token"
        );
    }

    @Override
    public void resetPassword(String identifier, String newPassword) {

        if (identifier == null || identifier.isBlank()) {
            throw new IllegalArgumentException(
                    "Username or Store Name is required"
            );
        }

        if (newPassword == null || newPassword.length() < 5) {
            throw new IllegalArgumentException(
                    "Password must contain at least 5 characters"
            );
        }

        User user = userRepository.findByUsername(identifier)
                .orElse(null);

        // Search by Store Name if username is not found
        if (user == null) {

            List<User> users =
                    userRepository.findAllByStoreName(identifier);

            if (users.isEmpty()) {
                throw new IllegalArgumentException("User not found");
            }

            if (users.size() > 1) {
                throw new IllegalArgumentException(
                        "Multiple users found with this Store Name. Please use Username."
                );
            }

            user = users.get(0);
        }

        // Encrypt password
        user.setPassword(passwordEncoder.encode(newPassword));

        // Save updated password
        userRepository.save(user);
    }

}
