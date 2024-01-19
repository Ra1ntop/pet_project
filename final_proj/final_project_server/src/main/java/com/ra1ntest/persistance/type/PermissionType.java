package com.ra1ntest.persistance.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PermissionType {

    ADMIN_CREATE("admin:create"),
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_DELETE("admin:delete"),

    CUSTOMER_CREATE("customer:create"),
    CUSTOMER_READ("admin:read"),
    CUSTOMER_UPDATE("admin:update"),
    CUSTOMER_DELETE("admin:delete");

    private final String permission;


}
