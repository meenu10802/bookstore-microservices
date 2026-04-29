package com.example.user_service.service;

import com.example.user_service.dto.PasswordChangeRequest;
import com.example.user_service.dto.ProfileRequest;
import com.example.user_service.dto.UserRequest;
import com.example.user_service.entity.Role;
import com.example.user_service.entity.User;
import com.example.user_service.repository.UserRepository;
import com.example.user_service.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository; //allows operations such as saving users, finding users
    //by email, and deleting users.

    @Autowired
    private JwtUtil jwtUtil; //used to generate and validate authentication tokens

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public String register(UserRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        //If the email is new, a user object is created.
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);
        return "User Registered";
    }

    public String login(UserRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

//if a user is found the entered password is compared with the stored encrypted password.
        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(user.getEmail(), user.getRole().name()); //and generates token
    }

    public User getProfile(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateProfile(String email, ProfileRequest req) {
        User user = getProfile(email);
        user.setName(req.getName());
        user.setPhone(req.getPhone());
        return userRepository.save(user);
    }

    public String changePassword(String email, PasswordChangeRequest req) {
        User user = getProfile(email);

        if (!encoder.matches(req.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Old password incorrect");
        }

        user.setPassword(encoder.encode(req.getNewPassword()));
        userRepository.save(user);

        return "Password updated successfully";
    }

    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User deleted";
    }
}