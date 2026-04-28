package com.example.notification_service.event;

public class UserEvent {

    private String type; // USER_REGISTERED
    private String userId;
    private String email;

    // No-argument constructor
    public UserEvent() {
    }

    // Parameterized constructor
    public UserEvent(String type, String userId, String email) {
        this.type = type;
        this.userId = userId;
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

    // Getter and Setter for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
