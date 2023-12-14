package com.example.instagram.config.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/security")
public class SecurityTestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/no-auth")
    public String noAuth() {
        return "no-auth";
    }

    @GetMapping("/auth")
    public String auth() {
        return "auth";
    }
}
