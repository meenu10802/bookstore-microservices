package com.example.user_service.controller;
import com.example.user_service.dto.PasswordChangeRequest;
import com.example.user_service.dto.ProfileRequest;
import com.example.user_service.dto.UserRequest;
import com.example.user_service.entity.Role;
import com.example.user_service.entity.User;
import com.example.user_service.security.JwtUtil;
import com.example.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @PostMapping("/register")
    public String register(@RequestBody UserRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserRequest request) {
        return userService.login(request);
    }

//here in each controller we are manually reading the JWT token and extracting the user’s user or email
//Instead of letting Spring Security automatically identify the logged-in user,
    @GetMapping("/profile")
    public User getProfile(@RequestHeader("Authorization") String token) {
        String email = jwtUtil.extractUsername(token.substring(7));
        return userService.getProfile(email); //the start index is from 7 because first 6 letters are
                                            //the word BEARER
    }

    @PutMapping("/profile")
    public User updateProfile(@RequestHeader("Authorization") String token,
                              @RequestBody ProfileRequest request) {
        String email = jwtUtil.extractUsername(token.substring(7));
        return userService.updateProfile(email, request);
    }

    @PutMapping("/change-password")
    public String changePassword(@RequestHeader("Authorization") String token,
                                 @RequestBody PasswordChangeRequest request) {
        String email = jwtUtil.extractUsername(token.substring(7));
        return userService.changePassword(email, request);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id,
                             @RequestHeader("Authorization") String token) {

        String email = jwtUtil.extractUsername(token.substring(7));
        User user = userService.getProfile(email);

        if (!user.getRole().equals(Role.ADMIN)) {
            throw new RuntimeException("Access denied");
        }

        return userService.deleteUser(id);
    }
    @GetMapping
    public List<User> getAllUsers(@RequestHeader("Authorization") String token) {
        String email = jwtUtil.extractUsername(token.substring(7));
        User user = userService.getProfile(email);
        if (!user.getRole().equals(Role.ADMIN)) {
            throw new RuntimeException("Access denied");
        }
        return userService.getAllUsers();
    }
    @GetMapping("/health")
    public String health() {
        return "User service is up";
    }
}