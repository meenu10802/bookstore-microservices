package com.example.notification_service.event;

public class OrderEvent {

    private String type;    // ORDER_PLACED, ORDER_SHIPPED, etc
    private String userId;
    private Long orderId;
    private String email;

    // No-argument constructor
    public OrderEvent() {
    }

    // Parameterized constructor
    public OrderEvent(String type, String userId, Long orderId, String email) {
        this.type = type;
        this.userId = userId;
        this.orderId = orderId;
        this.email = email;
    }

    // Getter and Setter for type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Getter and Setter for userId
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    // Getter and Setter for orderId
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    // Getter and Setter for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}