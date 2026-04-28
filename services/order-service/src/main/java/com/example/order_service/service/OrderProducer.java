package com.example.order_service.service;

import com.example.order_service.event.OrderEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public OrderProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendOrderEvent(OrderEvent event) {
        try {
            String json = objectMapper.writeValueAsString(event);
            kafkaTemplate.send("order-events", json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}