package com.example.admin_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ORDER-SERVICE", path = "/api/orders")
public interface OrderServiceClient {

    @GetMapping
    List<Object> getAllOrders();

    @PutMapping("/{id}/status")
    Object updateStatus(@PathVariable Long id, @RequestParam String status);
}