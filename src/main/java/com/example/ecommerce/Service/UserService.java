package com.example.ecommerce.Service;

import com.example.ecommerce.Model.User;
import com.example.ecommerce.Model.AuthTokens;

public interface UserService {

    User register(User user);
    User registerAdmin(User user);
    AuthTokens login(String username, String password);
    AuthTokens refresh(String refreshToken);
    User getUserByUsername(String username);

    void resetPassword(String identifier, String newPassword);
}
