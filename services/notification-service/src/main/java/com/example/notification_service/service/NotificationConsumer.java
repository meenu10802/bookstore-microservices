package com.example.notification_service.service;

import com.example.notification_service.event.OrderEvent;
import com.example.notification_service.event.UserEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Service
public class NotificationConsumer {

    @Autowired
    private EmailService emailService;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "order-events", groupId = "notification-group")
    public void onOrderEvent(String message) {
        try {
            OrderEvent event = objectMapper.readValue(message, OrderEvent.class);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "user-events", groupId = "notification-group")
    public void onUserEvent(String message) {
        try {
            UserEvent event = objectMapper.readValue(message, UserEvent.class);
            if ("USER_REGISTERED".equals(event.getType())) {
                emailService.sendWelcomeEmail(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}