package com.example.workflowengine.controller;

import com.example.workflowengine.dto.AuthResponse;
import com.example.workflowengine.dto.LoginRequest;
import com.example.workflowengine.service.AuthService;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:5173")

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request){
        return authService.login(request);
    }

}