package com.example.order_service.event;
<<<<<<< HEAD
=======

>>>>>>> feature/notification-service
public class OrderEvent {

    private String type;
    private String userId;
    private Long orderId;
    private String email;

<<<<<<< HEAD
    public OrderEvent() {
    }
=======
    public OrderEvent() {}
>>>>>>> feature/notification-service

    public OrderEvent(String type, String userId, Long orderId, String email) {
        this.type = type;
        this.userId = userId;
        this.orderId = orderId;
        this.email = email;
    }

<<<<<<< HEAD
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
=======
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
>>>>>>> feature/notification-service
