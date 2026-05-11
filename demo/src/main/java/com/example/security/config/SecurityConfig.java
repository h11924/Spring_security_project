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
                // 1. Disable CSRF so we can use Postman easily
                .csrf(customizer -> customizer.disable())

                // 2. Force authentication for every single page
                .authorizeHttpRequests(request -> request.anyRequest().authenticated())

                // 3. Enable the standard Login popup (Basic Auth)
                .httpBasic(Customizer.withDefaults())

                // 4. Make the app Stateless (No sessions stored on server)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

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