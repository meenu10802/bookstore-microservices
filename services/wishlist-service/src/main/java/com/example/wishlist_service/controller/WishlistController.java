package com.example.wishlist_service.controller;

import com.example.wishlist_service.entity.Wishlist;
import com.example.wishlist_service.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService service;

    @GetMapping
    public Wishlist getWishlist(@RequestParam String userId) {
        return service.getWishlist(userId);
    }

    @PostMapping("/add/{productId}")
    public Wishlist add(
            @RequestParam String userId,
            @PathVariable Long productId
    ) {
        return service.addItem(userId, productId);
    }

    @DeleteMapping("/remove/{productId}")
    public Wishlist remove(
            @RequestParam String userId,
            @PathVariable Long productId
    ) {
        return service.removeItem(userId, productId);
    }

    @DeleteMapping("/clear")
    public String clear(@RequestParam String userId) {
        service.clearWishlist(userId);
        return "Wishlist cleared";
    }
}