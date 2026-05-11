package com.example.security.controller;

import com.example.security.model.User; // Make sure this is imported
import com.example.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public User register(@RequestBody User user) { // Changed 'Users' to 'User'
        return service.register(user); // Changed 'user.register' to 'service.register'
    }
}