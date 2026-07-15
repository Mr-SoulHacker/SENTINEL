package com.sentinel.sentinel.controller;

import com.sentinel.sentinel.dto.AuthResponse;
import com.sentinel.sentinel.dto.LoginRequest;
import com.sentinel.sentinel.dto.RegisterRequest;
import com.sentinel.sentinel.service.AuthService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterRequest request
    ) {

        String result = authService.register(request);

        if (result.equals("Username already exists")) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Username already exists");
        }

        if (result.equals("Email already exists")) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Email already exists");
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}