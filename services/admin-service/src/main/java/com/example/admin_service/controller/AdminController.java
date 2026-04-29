package com.example.admin_service.controller;

import com.example.admin_service.client.OrderServiceClient;
import com.example.admin_service.client.ProductServiceClient;
import com.example.admin_service.dto.AdminRegisterRequest;
import com.example.admin_service.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.admin_service.client.UserServiceClient;
import java.util.List;

@RestController
        @RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private UserServiceClient userClient;
    @Autowired
    private ProductServiceClient productClient;

    @Autowired
    private OrderServiceClient orderClient;
    @Autowired
    private AdminService adminService;
    @GetMapping("/all-users")
    public List<Object> getAllUsers(@RequestHeader("Authorization") String token) {
        return userClient.getAllUsers(token);
    }
    @PostMapping("/register")
    public Object registerAdmin(@RequestBody AdminRegisterRequest request) {
        return adminService.registerAdmin(request);
    }
    @GetMapping("/orders")
    public List<Object> getAllOrders() {
        return orderClient.getAllOrders();
    }
    @PutMapping("/orders/{id}/status")
    public Object updateOrderStatus(@PathVariable Long id,
                                    @RequestParam String status) {
        return orderClient.updateStatus(id, status);
    }
    @PutMapping("/products/{id}")
    public Object updateProduct(@PathVariable Long id,
                                @RequestBody Object product) {
        return productClient.updateProduct(id, product);
    }
    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productClient.deleteProduct(id);
        return "Deleted successfully";
    }
}