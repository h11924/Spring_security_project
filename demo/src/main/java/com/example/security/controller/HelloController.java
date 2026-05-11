package com.example.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Tells Spring "I am a Controller!"
public class HelloController {

    @GetMapping("/") // What happens when you go to localhost:8080/
    public String greet() {
        return "Welcome to Harshitha's Secure App!";
    }

    @GetMapping("/hello") // What happens when you go to localhost:8080/hello
    public String hello() {
        return "Hello World! You have successfully logged in via PostgreSQL.";
    }
}
