package com.example.ecommerce.Service;

import com.example.ecommerce.Model.User;

public interface UserService {

    User register(User user);
    User registerAdmin(User user);
    String login(String username, String password);
}
