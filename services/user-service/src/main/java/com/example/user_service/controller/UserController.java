package com.example.user_service.controller;

import com.example.user_service.dto.UserRequest;
import com.example.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody UserRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserRequest request) {
        return userService.login(request);
    }

    @GetMapping("/health")
    public String health() {
        return "User service is up";
    }
}