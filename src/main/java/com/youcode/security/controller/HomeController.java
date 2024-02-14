package com.youcode.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/home")
public class HomeController {

    @GetMapping
    public String home(Principal principal) {
        return "Welcome to the home page " + principal.getName() + "!";
    }
}
