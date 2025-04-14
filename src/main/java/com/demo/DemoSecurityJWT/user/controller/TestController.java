package com.demo.DemoSecurityJWT.user.controller;

import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/test")
@SecurityRequirement(name = "BearerAuth")
public class TestController {

    @GetMapping
    public String helloSecure() {
        return "🔐 Hello! You are authenticated 🎉";
    }
}
