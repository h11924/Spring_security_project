package com.example.security.repo;

import com.example.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    // Spring automatically generates the SQL to find a user by their name
    User findByUsername(String username);
}