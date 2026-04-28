package com.example.notification_service.service;

import com.example.notification_service.event.OrderEvent;
import com.example.notification_service.event.UserEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "order-events", groupId = "notification-group")
    public void onOrderEvent(OrderEvent event) {

        switch (event.getType()) {

            case "ORDER_PLACED":
                emailService.sendOrderConfirmation(event);
                break;

            case "ORDER_SHIPPED":
                emailService.sendShippingUpdate(event);
                break;

            case "ORDER_DELIVERED":
                emailService.sendDeliveryConfirmation(event);
                break;
        }
    }

    @KafkaListener(topics = "user-events", groupId = "notification-group")
    public void onUserEvent(UserEvent event) {

        if ("USER_REGISTERED".equals(event.getType())) {
            emailService.sendWelcomeEmail(event);
        }
    }
}