package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Tells Spring this is a settings file
@EnableWebSecurity // Enables Spring Security for your project
public class SecurityConfig {

    @Bean // Tells Spring to manage this security guard (filter chain)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // 1. Disable CSRF (Cross-Site Request Forgery)
                // Since we are using Postman and making a stateless API, we don't need
                // the hidden security tokens that normal websites use.
                .csrf(customizer -> customizer.disable())

                // 2. Define access rules for different URLs
                .authorizeHttpRequests(request -> request
                        // This makes the "/register" door public.
                        // New users can create accounts without needing to log in first.
                        .requestMatchers("/register").permitAll()

                        // This locks every other door in the app.
                        // If a URL isn't "/register", you MUST provide a username and password.
                        .anyRequest().authenticated()
                )

                // 3. Enable Basic Authentication
                // This allows us to send credentials (username:password) in the header.
                // It also shows the login popup window in your browser.
                .httpBasic(Customizer.withDefaults())

                // 4. Set Session Management to STATELESS
                // This is vital for APIs. It tells the server: "Don't save the user's login session."
                // The user must send their credentials with every single request.
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 5. Finalize and build the security configuration
                .build();
    }
}