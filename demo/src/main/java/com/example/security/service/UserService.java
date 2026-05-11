package com.example.security.service;

import com.example.security.repo.UserRepo;
import com.example.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    // Change 'Users' to 'User' here!
    public User register(User user) {
        return repo.save(user);
    }
}
