package com.example.wishlist_service.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Wishlist {

    @Id
    private String userId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WishlistItem> items = new ArrayList<>();

    public Wishlist() {}

    public Wishlist(String userId) {
        this.userId = userId;
    }

    // Getter for userId
    public String getUserId() {
        return userId;
    }

    // Setter for userId
    public void setUserId(String userId) {
        this.userId = userId;
    }

    // Getter for items
    public List<WishlistItem> getItems() {
        return items;
    }

    // Setter for items
    public void setItems(List<WishlistItem> items) {
        this.items = items;
    }

    // Optional helper methods (recommended)

    public void addItem(WishlistItem item) {
        this.items.add(item);
    }

    public void removeItem(WishlistItem item) {
        this.items.remove(item);
    }
}