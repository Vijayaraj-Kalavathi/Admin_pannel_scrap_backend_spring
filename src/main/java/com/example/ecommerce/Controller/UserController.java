package com.example.ecommerce.Controller;

import com.example.ecommerce.Model.ResetPasswordRequest;
import com.example.ecommerce.Model.User;
import com.example.ecommerce.Model.AuthTokens;
import com.example.ecommerce.Model.RefreshTokenRequest;
import com.example.ecommerce.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(
            @RequestBody User user) {

        User createdUser = userService.register(user);

        Map<String, Object> response = new HashMap<>();

        response.put("message", "User registered successfully");
        response.put("data", createdUser);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/admin/register")
    public ResponseEntity<Map<String, Object>> registerAdmin(
            @RequestBody User user) {

        User createdAdminUser = userService.registerAdmin(user);

        Map<String, Object> response = new HashMap<>();

        response.put("message", "Admin User registered successfully");
        response.put("data", createdAdminUser);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestBody User user) {

        AuthTokens tokens = userService.login(
                user.getUsername(),
                user.getPassword()
        );

        // Fetch actual user details from database
        User existingUser = userService.getUserByUsername(
                user.getUsername()
        );

        Map<String, Object> response = new HashMap<>();

        response.put("message", "Login successful");
        response.put("token", tokens.accessToken());
        response.put("accessToken", tokens.accessToken());
        response.put("refreshToken", tokens.refreshToken());
        response.put("username", existingUser.getUsername());
        response.put("user_id", existingUser.getId());
        response.put("storeName", existingUser.getStoreName());
        response.put("role", existingUser.getRole());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<Map<String, Object>> refresh(
            @RequestBody RefreshTokenRequest request) {

        AuthTokens tokens = userService.refresh(request.refreshToken());
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Token refreshed successfully");
        response.put("token", tokens.accessToken());
        response.put("accessToken", tokens.accessToken());
        response.put("refreshToken", tokens.refreshToken());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Map<String, Object>> resetPassword(
            @RequestBody ResetPasswordRequest request) {

        userService.resetPassword(
                request.getIdentifier(),
                request.getNewPassword()
        );

        Map<String, Object> response = new HashMap<>();

        response.put("success", true);
        response.put("message", "Password reset successfully");

        return ResponseEntity.ok(response);
    }

}
