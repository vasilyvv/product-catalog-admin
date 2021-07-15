package com.my.task.productcatalogadmin.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role {

    USER(new HashSet<>(Collections.singletonList(
            Permission.ROLES_READ
    ))),

    ADMIN(new HashSet<>(Arrays.asList(
            Permission.ROLES_READ,
            Permission.ROLES_WRITE
    )));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
