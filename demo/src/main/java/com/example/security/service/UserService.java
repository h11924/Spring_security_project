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

    // We create the encoder with a strength of 12 rounds
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    /**
     * This method handles the registration of a new user.
     * It takes the raw password, encrypts it using BCrypt,
     * and then saves the user to the database.
     */
    public User register(User user) {
        // 1. Get the plain password (e.g., "h123") from the request
        // 2. Encode it (scramble it)
        // 3. Set the scrambled string back into the user object
        user.setPassword(encoder.encode(user.getPassword()));

        // 4. Save the user to PostgreSQL and return the saved object
        return repo.save(user);
    }
}