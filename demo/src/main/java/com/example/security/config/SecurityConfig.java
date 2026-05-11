package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                // 1. Disable CSRF (Common for REST APIs)
                .csrf(customizer -> customizer.disable())

                // 2. All requests must be authenticated
                .authorizeHttpRequests(request -> request.anyRequest().authenticated())

                // 3. Enable HTTP Basic (Username/Password prompt)
                .httpBasic(Customizer.withDefaults())

                // 4. Make the server "Stateless" (No memory/No sessions)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 5. Finalize and build the chain
                .build();
    }
}