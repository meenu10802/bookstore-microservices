package com.example.order_service.service;

import com.example.order_service.client.CartClient;
import com.example.order_service.entity.Order;
import com.example.order_service.entity.OrderItem;
import com.example.order_service.event.OrderEvent;
import com.example.order_service.repository.OrderRepository;
import com.example.order_service.service.OrderProducer;
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

    @Autowired
    private OrderProducer orderProducer; // ← ADD THIS

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

        // ← ADD THIS — send Kafka event after order is saved
        orderProducer.sendOrderEvent(new OrderEvent(
                "ORDER_PLACED",
                userId,
                saved.getId(),
                null  // replace null with email if you have it
        ));

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

        Order updated = repository.save(order);

        // ← ADD THIS — send Kafka event when status changes
        orderProducer.sendOrderEvent(new OrderEvent(
                updated.getStatus(),  // "ORDER_SHIPPED" or "ORDER_DELIVERED"
                updated.getUserId(),
                updated.getId(),
                null
        ));

        return updated;
    }
}