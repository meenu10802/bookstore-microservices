package com.example.cart_service.entity;

import java.math.BigDecimal;

public class CartItem {

    private Long productId;
    private String productTitle;
    private int quantity;
    private BigDecimal unitPrice;
    public CartItem(Long productId, String productTitle, int quantity, BigDecimal unitPrice) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
    // GETTERS & SETTERS

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}