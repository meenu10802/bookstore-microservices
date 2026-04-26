package com.example.admin_service.service;

import com.example.admin_service.dto.AdminRegisterRequest;
import com.example.admin_service.entity.Admin;
import com.example.admin_service.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin registerAdmin(AdminRegisterRequest request) {
        Admin admin = new Admin();
        admin.setName(request.getName());
        admin.setEmail(request.getEmail());
        admin.setRole(request.getRole());

        return adminRepository.save(admin);
    }
}