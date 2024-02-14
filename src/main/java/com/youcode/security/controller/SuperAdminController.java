package com.youcode.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/super-admin")
@PreAuthorize("hasRole('SUPER_ADMIN')")
public class SuperAdminController {

    @GetMapping
    @PreAuthorize("hasAnyAuthority('super_admin:read')")
    public String get() {
        return " GET : Welcome to the super admin page!";
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('super_admin:po')")
    public String post() {
        return "POST : Welcome to the super admin page!";
    }
    @PostMapping
    @PreAuthorize("hasAnyAuthority('super_admin:update')")
    public String put() {
        return "PUT : Welcome to the super admin page!";
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('super_admin:delete')")
    public String delete() {
        return "DELETE : Welcome to the super admin page!";
    }
}
