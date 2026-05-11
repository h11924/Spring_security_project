package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // 1. Disable CSRF:
                // We do this because we are making a "Stateless" API.
                // It prevents Spring from asking for a hidden token with every request,
                // making it much easier to test with tools like Postman.
                .csrf(customizer -> customizer.disable())

                // 2. Authorize Http Requests:
                // This is the main "Logic Gate" where we define who can see which page.
                .authorizeHttpRequests(request -> request

                        // 3. Match /register and Permit All:
                        // This is the "Public Door." It tells the guard:
                        // "If someone comes to the /register link, let them in without asking for an ID."
                        .requestMatchers("/register").permitAll()

                        // 4. Any Request Authenticated:
                        // This is the "Security Guard" rule for everything else.
                        // "For any other page in this app, you MUST have a valid username and password."
                        .anyRequest().authenticated()
                )

                // 5. HTTP Basic:
                // This enables that little "Login Popup" window you see in the browser.
                // It tells the app to use the standard username/password login method.
                .httpBasic(Customizer.withDefaults())

                // 6. Session Management (Stateless):
                // This tells the server: "Don't remember the user after they log in."
                // Every single time the user clicks a button, they must send their ID again.
                // This is like a high-security building where you must show your badge at every single door.
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 7. Build:
                // This finally "locks in" all the rules above and creates the security guard.
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        /* * This is the missing piece!
         * It tells Spring: "My database passwords are NOT encrypted.
         * Just compare the raw strings."
         * * Note: Navin uses this for learning, but in the very next video,
         * he will show you BCrypt for real security.
         */
        return NoOpPasswordEncoder.getInstance();
    }
}

//we need to implement bycrt when taling password and also when validating in
