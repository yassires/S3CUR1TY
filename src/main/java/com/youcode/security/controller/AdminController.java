package com.youcode.security.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @GetMapping
    public String get() {
        return " GET : Welcome to the admin page!";
    }

    @PostMapping
    public String post() {
        return "POST : Welcome to the admin page!";
    }
    @PostMapping
    public String put() {
        return "PUT : Welcome to the admin page!";
    }

    @DeleteMapping
    public String delete() {
        return "DELETE : Welcome to the admin page!";
    }
}
