package com.example.admin_service.service;

import com.example.admin_service.dto.AdminRegisterRequest;
import com.example.admin_service.entity.Admin;
import com.example.admin_service.entity.AdminRole;
import com.example.admin_service.repository.AdminRepository;
import com.example.admin_service.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // POST /api/admin/register — no auth, creates first SUPER_ADMIN
    public String registerSuperAdmin(AdminRegisterRequest request) {
        if (adminRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        Admin admin = new Admin();
        admin.setEmail(request.getEmail());
        admin.setPassword(encoder.encode(request.getPassword()));
        admin.setName(request.getName());
        admin.setRole(AdminRole.SUPER_ADMIN);
        adminRepository.save(admin);
        return "Super Admin registered successfully";
    }

    // POST /api/admin/create-admin — SUPER_ADMIN creates a new ADMIN
    public String createAdmin(AdminRegisterRequest request) {
        if (adminRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        Admin admin = new Admin();
        admin.setEmail(request.getEmail());
        admin.setPassword(encoder.encode(request.getPassword()));
        admin.setName(request.getName());
        admin.setRole(AdminRole.ADMIN);
        adminRepository.save(admin);
        return "Admin created successfully";
    }

    // POST /api/admin/login — returns JWT with role claim
    public String login(AdminRegisterRequest request) {
        Admin admin = adminRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        if (!encoder.matches(request.getPassword(), admin.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(admin.getEmail(), admin.getRole().name());
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public String deleteAdmin(Long id) {
        adminRepository.deleteById(id);
        return "Admin deleted";
    }
}