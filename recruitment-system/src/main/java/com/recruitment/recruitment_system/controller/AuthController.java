package com.recruitment.recruitment_system.controller;

import com.recruitment.recruitment_system.dto.LoginRequest;
import com.recruitment.recruitment_system.dto.RegisterRequest;
import com.recruitment.recruitment_system.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody RegisterRequest request) {

        String message = authService.register(request);

        return Map.of("message", message);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {

        Map<String, String> response = authService.login(request);

        return response;
    }
}