package com.example.order_service.service;

import com.example.order_service.event.OrderEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
<<<<<<< HEAD
=======
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> feature/notification-service
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

<<<<<<< HEAD
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public OrderProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }
=======
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;
>>>>>>> feature/notification-service

    public void sendOrderEvent(OrderEvent event) {
        try {
            String json = objectMapper.writeValueAsString(event);
<<<<<<< HEAD
            kafkaTemplate.send("order-events", json);
=======
            kafkaTemplate.send("order-events", json)
                    .whenComplete((result, ex) -> {
                        if (ex != null) {
                            System.err.println("Failed to send event: " + ex.getMessage());
                        } else {
                            System.out.println("Event sent: " + json);
                        }
                    });
>>>>>>> feature/notification-service
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}