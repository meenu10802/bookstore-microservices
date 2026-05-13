package com.example.admin_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// "order-service" must match spring.application.name in order-service exactly
@FeignClient(name = "order-service")
public interface OrderFeignClient {

    // Matches GET /api/orders in OrderController (the getAll() method)
    @GetMapping("/api/orders")
    List<Object> getAllOrders();

    // Matches PUT /api/orders/{id}/status in OrderController
    // order-service uses @RequestParam String status — so we send it as a query param too
    @PutMapping("/api/orders/{id}/status")
    Object updateOrderStatus(@PathVariable("id") Long id,
                             @RequestParam("status") String status);
}