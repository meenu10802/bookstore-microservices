package com.example.order_service.service;

import com.example.order_service.client.CartClient;
import com.example.order_service.entity.Order;
import com.example.order_service.entity.OrderItem;
import com.example.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private CartClient cartClient;

    @Autowired
    private OrderRepository repository;

    public Order placeOrder(String userId) {

        CartClient.CartResponse cart = cartClient.getCart(userId);

        if (cart.items == null || cart.items.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();
        order.setUserId(userId);
        order.setTotalAmount(cart.totalAmount);

        order.setItems(
                cart.items.stream().map(item -> {
                    OrderItem oi = new OrderItem();
                    oi.setProductId(item.productId);
                    oi.setProductTitle(item.productTitle);
                    oi.setQuantity(item.quantity);
                    oi.setPrice(item.unitPrice);
                    return oi;
                }).collect(Collectors.toList())
        );

        Order saved = repository.save(order);

        cartClient.clearCart(userId);

        return saved;
    }

    public Order getOrder(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public Order updateStatus(Long id, String status) {
        Order order = repository.findById(id).orElseThrow();
        order.setStatus(status);
        return repository.save(order);
    }
}