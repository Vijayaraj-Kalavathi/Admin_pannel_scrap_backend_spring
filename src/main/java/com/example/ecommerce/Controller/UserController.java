package com.example.ecommerce.Controller;

import com.example.ecommerce.Model.User;
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

        String token = userService.login(
                user.getUsername(),
                user.getPassword()
        );

        // Fetch actual user details from database
        User existingUser = userService.getUserByUsername(
                user.getUsername()
        );

        Map<String, Object> response = new HashMap<>();

        response.put("message", "Login successful");
        response.put("token", token);
        response.put("username", existingUser.getUsername());
        response.put("user_id", existingUser.getId());
        response.put("storeName", existingUser.getStoreName());
        return ResponseEntity.ok(response);
    }

}
