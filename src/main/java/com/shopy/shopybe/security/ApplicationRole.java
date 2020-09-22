package com.shopy.shopybe.security;

import java.util.Set;

import static com.shopy.shopybe.security.Permission.*;
import static java.util.Set.of;


public enum ApplicationRole {
    ROLE_USER(of(PLACE_ORDER, ADD_PRODUCT_TO_FAVORITES, REMOVE_PRODUCT_FROM_FAVORITES, CANCEL_ORDER, RATE_PRODUCT)),
    ROLE_MODERATOR(of(ADD_PRODUCT, DELETE_PRODUCT, EDIT_PRODUCT, PLACE_ORDER_FOR_OTHER_USER)),
    ROLE_ADMIN(of(CHANGE_ROLE, BLOCK_USER));

    private final Set<Permission> permissions;

    ApplicationRole(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
}