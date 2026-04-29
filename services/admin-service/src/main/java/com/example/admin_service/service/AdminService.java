package com.example.admin_service.service;

import com.example.admin_service.client.UserServiceClient;
import com.example.admin_service.dto.AdminRegisterRequest;
import com.example.admin_service.dto.UserResponse;
import com.example.admin_service.entity.Admin;
import com.example.admin_service.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserServiceClient userClient;

    public void checkAdmin(String token) {
        UserResponse user = userClient.getProfile(token);

        if (!user.getRole().equals("ADMIN") && !user.getRole().equals("SUPER_ADMIN")) {
            throw new RuntimeException("Access denied. Admin only.");
        }
    }

    public void checkSuperAdmin(String token) {
        UserResponse user = userClient.getProfile(token);

        if (!user.getRole().equals("SUPER_ADMIN")) {
            throw new RuntimeException("Access denied. Super admin only.");
        }
    }

    public Admin registerAdmin(String token, AdminRegisterRequest request) {
        System.out.println("TOKEN RECEIVED IN ADMIN SERVICE: " + token);

        checkSuperAdmin(token);

        System.out.println("TOKEN SENT TO USER SERVICE: " + token);

        userClient.registerAdmin(token, request);

        Admin admin = new Admin();
        admin.setName(request.getName());
        admin.setEmail(request.getEmail());
        admin.setRole("ADMIN");

        return adminRepository.save(admin);
    }
}