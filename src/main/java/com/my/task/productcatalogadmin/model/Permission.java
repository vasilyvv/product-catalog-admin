package com.my.task.productcatalogadmin.model;

public enum Permission {

    ROLES_READ("role:read"),
    ROLES_WRITE("role:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
