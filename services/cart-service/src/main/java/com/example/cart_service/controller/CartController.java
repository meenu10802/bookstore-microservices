package com.example.cart_service.controller;

import com.example.cart_service.entity.Cart;
import com.example.cart_service.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService service;

    @GetMapping("/{userId}")
    public Cart getCart(@PathVariable String userId) {
        return service.getCart(userId);
    }

    @PostMapping("/add")
    public Cart addItem(
            @RequestParam String userId,
            @RequestParam Long productId,
            @RequestParam int quantity
    ) {
        return service.addItem(userId, productId, quantity);
    }

    @PutMapping("/update")
    public Cart updateItem(
            @RequestParam String userId,
            @RequestParam Long productId,
            @RequestParam int quantity
    ) {
        return service.updateItem(userId, productId, quantity);
    }

    @DeleteMapping("/remove/{productId}")
    public Cart removeItem(
            @RequestParam String userId,
            @PathVariable Long productId
    ) {
        return service.removeItem(userId, productId);
    }

    @DeleteMapping("/clear")
    public String clear(@RequestParam String userId) {
        service.clearCart(userId);
        return "Cart cleared";
    }

    @GetMapping("/total")
    public BigDecimal total(@RequestParam String userId) {
        return service.getTotal(userId);
    }
}