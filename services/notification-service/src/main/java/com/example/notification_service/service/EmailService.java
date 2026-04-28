package com.example.notification_service.service;

import com.example.notification_service.event.OrderEvent;
import com.example.notification_service.event.UserEvent;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void sendOrderConfirmation(OrderEvent event) {
        System.out.println("ORDER CONFIRMATION EMAIL");
        System.out.println("To user: " + event.getUserId());
        System.out.println("Order ID: " + event.getOrderId());
    }

    public void sendShippingUpdate(OrderEvent event) {
        System.out.println("SHIPPING UPDATE EMAIL");
        System.out.println("Order ID: " + event.getOrderId());
    }

    public void sendDeliveryConfirmation(OrderEvent event) {
        System.out.println("DELIVERY CONFIRMATION EMAIL");
        System.out.println("Order ID: " + event.getOrderId());
    }

    public void sendWelcomeEmail(UserEvent event) {
        System.out.println("WELCOME EMAIL");
        System.out.println("To: " + event.getEmail());
    }
}