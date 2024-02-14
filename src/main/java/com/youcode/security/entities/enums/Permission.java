package com.youcode.security.entities.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Permission {

    USER_READ("user:read"),
    USER_CREATE("user:create"),
    USER_DELETE("user:delete"),
    USER_UPDATE("user:update"),

    ADMIN_READ("admin:read"),
    ADMIN_CREATE("admin:craete"),
    ADMIN_DELETE("admin:delete"),
    ADMIN_UPDATE("admin:update"),

    SUPER_ADMIN_READ("superAdmin:read"),
    SUPER_ADMIN_CREATE("superAdmin:create"),
    SUPER_ADMIN_DELETE("superAdmin:delete"),
    SUPER_ADMIN_UPDATE("superAdmin:update");


    private final String permission;



}
