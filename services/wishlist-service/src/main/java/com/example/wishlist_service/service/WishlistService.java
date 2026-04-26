package com.example.wishlist_service.service;

import com.example.wishlist_service.client.ProductClient;
import com.example.wishlist_service.entity.Wishlist;
import com.example.wishlist_service.entity.WishlistItem;
import com.example.wishlist_service.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository repository;

    @Autowired
    private ProductClient productClient;

    public Wishlist getWishlist(String userId) {
        return repository.findById(userId)
                .orElse(new Wishlist(userId));
    }

    public Wishlist addItem(String userId, Long productId) {

        Wishlist wishlist = getWishlist(userId);

        ProductClient.ProductResponse product =
                productClient.getProduct(productId);

        WishlistItem item = new WishlistItem(
                productId,
                product.title,
                product.price
        );

        wishlist.getItems().add(item);

        return repository.save(wishlist);
    }

    public Wishlist removeItem(String userId, Long productId) {

        Wishlist wishlist = getWishlist(userId);

        wishlist.getItems().removeIf(
                item -> item.getProductId().equals(productId)
        );

        return repository.save(wishlist);
    }

    public void clearWishlist(String userId) {
        repository.deleteById(userId);
    }
}
