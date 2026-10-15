package com.example.ecommerce.Service;

import com.example.ecommerce.Model.User;
import com.example.ecommerce.Repository.UserRepository;
import com.example.ecommerce.Security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtservice;

    public UserServiceImp(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder, JwtService jwtservice) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtservice = jwtservice;
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
    public String login(String username, String password) {

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

        // 3. Generate JWT
        return JwtService.generateToken(
                user.getUsername()
        );
    }

}
