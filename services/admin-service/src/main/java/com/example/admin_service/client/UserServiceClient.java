package com.example.admin_service.client;

import com.example.admin_service.dto.AdminRegisterRequest;
import com.example.admin_service.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user-service", path = "/api/users")
public interface UserServiceClient {

    @GetMapping("/profile")
    UserResponse getProfile(@RequestHeader(value = "Authorization") String token);

    @GetMapping
    List<Object> getAllUsers(@RequestHeader(value = "Authorization") String token);

    @PostMapping("/register-admin")
    String registerAdmin(@RequestHeader(value = "Authorization") String token,
                         @RequestBody AdminRegisterRequest request);
}