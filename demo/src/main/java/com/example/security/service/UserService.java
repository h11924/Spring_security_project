package com.example.security.service;

import com.example.security.model.User;
import com.example.security.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    // Create the encoder once. 12 rounds is the standard "sweet spot" for speed and security.
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User register(User user) {
        // Take the plain password (e.g. "h123"), turn it into a hash, and save that instead.
        user.setPassword(encoder.encode(user.getPassword()));

        // Save the user with the long encrypted password to PostgreSQL
        return repo.save(user);
    }
}