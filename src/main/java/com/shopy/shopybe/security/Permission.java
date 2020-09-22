package com.shopy.shopybe.security;


public enum Permission{
    PLACE_ORDER ("place_order"),
    CANCEL_ORDER ("cancel_order"),
    RATE_PRODUCT ("rate_product"),
    ADD_PRODUCT_TO_FAVORITES ("add_product_to_favorites"),
    REMOVE_PRODUCT_FROM_FAVORITES ("remove_product_from_favorites"),
    ADD_PRODUCT ("add_product"),
    DELETE_PRODUCT ("delete_product"),
    EDIT_PRODUCT ("edit_product"),
    PLACE_ORDER_FOR_OTHER_USER ("place_order_for_other_user"),
    CHANGE_ROLE ("change_role"),
    BLOCK_USER ("block_user");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
