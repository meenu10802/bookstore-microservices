package com.example.admin_service.controller;

import com.example.admin_service.client.OrderFeignClient;
import com.example.admin_service.client.ProductServiceClient;
import com.example.admin_service.dto.AdminRegisterRequest;
import com.example.admin_service.entity.Admin;
import com.example.admin_service.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired private AdminService adminService;
    @Autowired private OrderFeignClient orderFeignClient;
    @Autowired private ProductServiceClient productFeignClient;

    @GetMapping("/health")
    public String health() {
        return "Admin service is up";
    }

    // POST /api/admin/register — NO AUTH — one-time SUPER_ADMIN bootstrap
    @PostMapping("/register")
    public String registerSuperAdmin(@RequestBody AdminRegisterRequest request) {
        return adminService.registerSuperAdmin(request);
    }

    // POST /api/admin/login — NO AUTH — returns JWT token
    @PostMapping("/login")
    public String login(@RequestBody AdminRegisterRequest request) {
        return adminService.login(request);
    }

    // POST /api/admin/create-admin — SUPER_ADMIN only
    @PostMapping("/create-admin")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public String createAdmin(@RequestBody AdminRegisterRequest request) {
        return adminService.createAdmin(request);
    }

    // GET /api/admin/all-admins — SUPER_ADMIN only
    @GetMapping("/all-admins")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    // DELETE /api/admin/{id} — SUPER_ADMIN only
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public String deleteAdmin(@PathVariable Long id) {
        return adminService.deleteAdmin(id);
    }

    // GET /api/admin/all-users — ADMIN or SUPER_ADMIN
    @GetMapping("/all-users")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    public List<Admin> getAllUsers() {
        return adminService.getAllAdmins();
    }

    // GET /api/admin/orders — ADMIN or SUPER_ADMIN — calls order-service via Feign
    @GetMapping("/orders")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    public List<Object> getAllOrders() {
        return orderFeignClient.getAllOrders();
    }

    // PUT /api/admin/orders/{id}/status — ADMIN or SUPER_ADMIN
    // Body: { "status": "SHIPPED" }
    // Map<String, String> reads the JSON body — we extract "status" key directly
    // No separate DTO class needed
    @PutMapping("/orders/{id}/status")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    public Object updateOrderStatus(@PathVariable Long id,
                                    @RequestBody Map<String, String> body) {
        String status = body.get("status");
        return orderFeignClient.updateOrderStatus(id, status);
    }

    // PUT /api/admin/products/{id} — ADMIN or SUPER_ADMIN — calls product-service via Feign
    // Body: { "title": "...", "price": 99.9, "author": "..." }
    @PutMapping("/products/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    public Object updateProduct(@PathVariable Long id,
                                @RequestBody Map<String, Object> product) {
        return productFeignClient.updateProduct(id, product);
    }

    // DELETE /api/admin/products/{id} — ADMIN or SUPER_ADMIN
    @DeleteMapping("/products/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    public String deleteProduct(@PathVariable Long id) {
        productFeignClient.deleteProduct(id);
        return "Product deleted";
    }
}