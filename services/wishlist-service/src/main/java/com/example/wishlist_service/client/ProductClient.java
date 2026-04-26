package com.example.wishlist_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductClient {

    @GetMapping("/api/products/{id}")
    ProductResponse getProduct(@PathVariable Long id);

    class ProductResponse {
        public Long id;
        public String title;
        public BigDecimal price;
    }
}