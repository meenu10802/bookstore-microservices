package com.example.order_service.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private BigDecimal totalAmount;

    private LocalDateTime createdAt;
    private String status;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderItem> items;

    // Constructors
    public Order() {
        this.createdAt = LocalDateTime.now();
    }

    public Order(Long id, String userId, BigDecimal totalAmount,
                 LocalDateTime createdAt, List<OrderItem> items) {
        this.id = id;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
        this.items = items;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public String getStatus(){
        return status;
    }
    public List<OrderItem> getItems() {
        return items;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }
public void setStatus(String status){
    this.status = status;
}
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}