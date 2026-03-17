package com.recruitment.recruitment_system.service;

import com.recruitment.recruitment_system.dto.LoginRequest;
import com.recruitment.recruitment_system.dto.RegisterRequest;
import com.recruitment.recruitment_system.model.User;
import com.recruitment.recruitment_system.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public String register(RegisterRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());

        userRepository.save(user);

        return "User registered successfully";
    }

    public Map<String, String> login(LoginRequest request) {

        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        Map<String, String> response = new HashMap<>();

        if (userOptional.isEmpty()) {
            response.put("error", "User not found");
            return response;
        }

        User user = userOptional.get();

        if (!user.getPassword().equals(request.getPassword())) {
            response.put("error", "Invalid password");
            return response;
        }

        String token = "dummy-token";

        response.put("token", token);
        response.put("role", user.getRole().name());

        return response;
    }
}