package com.shopy.shopybe.entity;

public enum OrderStatus {
    SUBMITTED("Submitted"),
    ON_THE_WAY_TO_DELIVERY_COMPANY("On the way to delivery company"),
    IN_DELIVERY_COMPANY_STORAGE("In delivery company storage"),
    IN_DELIVERY("In delivery"),
    DELIVERED("Delivered");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }
}
