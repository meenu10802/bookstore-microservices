package com.example.cart_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

//Create a client that talks to another microservice named PRODUCT-SERVICE
@FeignClient(name = "PRODUCT-SERVICE")

//Spring + OpenFeign will automatically generate the HTTP call instead of using RestTemplate / WebClient
public interface ProductClient {

    @GetMapping("/api/products/{id}")
    ProductResponse getProduct(@PathVariable Long id);

    class ProductResponse {
        public Long id;
        public String title;
        public BigDecimal price;
    }
}