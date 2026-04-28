package com.example.order_service.service;

import com.example.order_service.event.OrderEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendOrderEvent(OrderEvent event) {
        try {
            String json = objectMapper.writeValueAsString(event);
            kafkaTemplate.send("order-events", json)
                    .whenComplete((result, ex) -> {
                        if (ex != null) {
                            System.err.println("Failed to send event: " + ex.getMessage());
                        } else {
                            System.out.println("Event sent: " + json);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}