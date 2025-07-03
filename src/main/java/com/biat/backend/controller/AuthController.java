package com.biat.backend.controller;

import com.biat.backend.dto.AuthRequest;
import com.biat.backend.dto.AuthResponse;
import com.biat.backend.service.AuthService;
import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.authenticate(request);
    }

    @PostMapping("/get-token")
    public String getToken(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        return authService.getToken(email);
    }

}
