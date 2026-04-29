package com.example.admin_service.controller;

import com.example.admin_service.client.OrderServiceClient;
import com.example.admin_service.client.ProductServiceClient;
import com.example.admin_service.client.UserServiceClient;
import com.example.admin_service.dto.AdminRegisterRequest;
import com.example.admin_service.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/register")
    public Object registerAdmin(@RequestHeader("Authorization") String token,
                                @RequestBody AdminRegisterRequest request) {
        return adminService.registerAdmin(token, request);
    }

    @GetMapping("/all-users")
    public List<Object> getAllUsers(@RequestHeader("Authorization") String token) {
        adminService.checkAdmin(token);
        return userClient.getAllUsers(token);
    }

    @PutMapping("/products/{id}")
    public Object updateProduct(@RequestHeader("Authorization") String token,
                                @PathVariable Long id,
                                @RequestBody Object product) {
        adminService.checkAdmin(token);
        return productClient.updateProduct(id, product);
    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(@RequestHeader("Authorization") String token,
                                @PathVariable Long id) {
        adminService.checkAdmin(token);
        productClient.deleteProduct(id);
        return "Product deleted successfully";
    }

    @GetMapping("/orders")
    public List<Object> getAllOrders(@RequestHeader("Authorization") String token) {
        adminService.checkAdmin(token);
        return orderClient.getAllOrders();
    }

    @PutMapping("/orders/{id}/status")
    public Object updateOrderStatus(@RequestHeader("Authorization") String token,
                                    @PathVariable Long id,
                                    @RequestParam String status) {
        adminService.checkAdmin(token);
        return orderClient.updateStatus(id, status);
    }
}