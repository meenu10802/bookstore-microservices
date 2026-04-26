package com.example.cart_service.service;

import com.example.cart_service.client.ProductClient;
import com.example.cart_service.entity.Cart;
import com.example.cart_service.entity.CartItem;
import com.example.cart_service.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository repository;

    @Autowired
    private ProductClient productClient;

    public Cart getCart(String userId) {
        return repository.findById(userId).orElse(new Cart(userId));
    }

    public Cart addItem(String userId, Long productId, int quantity) {

        Cart cart = getCart(userId);

        ProductClient.ProductResponse product = productClient.getProduct(productId);

        CartItem item = new CartItem(
                productId,
                product.title,
                quantity,
                product.price
        );

        cart.getItems().add(item);

        calculateTotal(cart);

        return repository.save(cart);
    }

    public Cart updateItem(String userId, Long productId, int quantity) {

        Cart cart = getCart(userId);

        for (CartItem item : cart.getItems()) {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(quantity);
            }
        }

        calculateTotal(cart);
        return repository.save(cart);
    }

    public Cart removeItem(String userId, Long productId) {

        Cart cart = getCart(userId);

        cart.getItems().removeIf(i -> i.getProductId().equals(productId));

        calculateTotal(cart);
        return repository.save(cart);
    }

    public void clearCart(String userId) {
        repository.deleteById(userId);
    }

    public BigDecimal getTotal(String userId) {
        Cart cart = getCart(userId);
        return cart.getTotalAmount();
    }

    private void calculateTotal(Cart cart) {
        BigDecimal total = BigDecimal.ZERO;

        for (CartItem item : cart.getItems()) {
            total = total.add(
                    item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity()))
            );
        }

        cart.setTotalAmount(total);
    }
}