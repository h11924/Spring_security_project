package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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

    // --- PART 2: DEFINING THE USERS (The notebook for the guard) ---
    @Bean
    public UserDetailsService userDetailsService() {

        /* * UserDetails represents a single person in your system.
         * We use the 'User.withDefaultPasswordEncoder()' for learning,
         * but in real life, you'd use an encoder like BCrypt.
         */

        UserDetails user1 = User
                .withDefaultPasswordEncoder() // Tells Spring to treat the password as plain text (deprecated/unsafe)
                .username("kiran")           // The login name
                .password("k@123")           // The login password
                .roles("USER")               // A basic role for this user
                .build();                    // Finalizes the creation of the user object

        UserDetails user2 = User
                .withDefaultPasswordEncoder()
                .username("harsh")
                .password("h@123")
                .roles("ADMIN")              // This user has more power (Admin)
                .build();

        /*
         * InMemoryUserDetailsManager is a class that implements UserDetailsService.
         * It stores the users in the RAM (memory) of the application.
         * When you restart the app, any changes made to users are lost.
         */
        return new InMemoryUserDetailsManager(user1, user2);
    }

}