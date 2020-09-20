package com.shopy.shopybe.entity;

import lombok.Getter;

@Getter
public enum ProductCategory {
    GAMING ("Gaming"),
    LAPTOPS_AND_ACCESSORIES ("Laptops and accessories"),
    TV ("TV");

    private final String value;

    ProductCategory(String value) {
        this.value = value;
    }
}
