package com.example.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@FeignClient(name = "cart-service")
public interface CartClient {

    @GetMapping("/api/cart/{userId}")
    CartResponse getCart(@PathVariable String userId);

    @DeleteMapping("/api/cart/clear")
    void clearCart(@RequestParam String userId);

    class CartResponse {
        public String userId;
        public List<CartItem> items;
        public BigDecimal totalAmount;
    }

    class CartItem {
        public Long productId;
        public String productTitle;
        public int quantity;
        public BigDecimal unitPrice;
    }
}