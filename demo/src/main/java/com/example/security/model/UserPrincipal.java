package com.example.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

public class UserPrincipal implements UserDetails {

    private User user; // Our custom User object from the database
    //"I am going to store a real person from my database inside you."

    public UserPrincipal(User user) {
        this.user = user;
    }

    @Override//Hey, I know this method already exists in the Spring Security 'blueprint'.
    // I am now giving you my own version of how this method should work.
    
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Returning an empty list for now (no specific roles yet)
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}

/**
 * Because it's an interface, it's basically a contract.
 * Spring says: "I don't care where your user data comes from (PostgreSQL,
 * MySQL, or a text file), as long as you wrap it in this UserDetails
 * interface so I know where to find the username and password.
 *
 * Why we "Implement" it
 * When you wrote public class UserPrincipal implements UserDetails, you were basically telling the Spring engine:
 *
 * "Hey Spring, I know you're looking for a UserDetails object.
 * I have this UserPrincipal class that follows all your rules (methods).
 * Inside it, I've hidden my actual database User.
 * Every time you ask for a password,
 * I'll go grab it from my database object and give it to you."
 *
 * so everytime we are adding data we will add it thriugh user
 * model but that data is accesesd by userdeatls which makes it able to login
 *
 *
 */