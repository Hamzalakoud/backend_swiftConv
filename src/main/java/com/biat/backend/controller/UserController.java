package com.biat.backend.controller;

import com.biat.backend.dto.AuthResponse;
import com.biat.backend.dto.RegisterRequest;
import com.biat.backend.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.biat.backend.model.User;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Helper method to extract token
    private String extractToken(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return authHeader;
    }

    // Updated method requiring Authorization header and passing token
    @GetMapping
    public List<User> getAllUsers(@RequestHeader("Authorization") String authHeader) {
        String token = extractToken(authHeader);
        return userService.getAllUsers(token);
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @GetMapping("/id")
    public Long getUserIdByEmail(@RequestParam String email) {
        return userService.getUserIdByEmail(email);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PutMapping("/update/{id}")
    public AuthResponse updateUser(
            @PathVariable Long id,
            @RequestBody RegisterRequest request,
            @RequestHeader("Authorization") String authHeader
    ) {
        String token = extractToken(authHeader);
        return userService.updateUser(id, request, token);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(
            @PathVariable Long id,
            @RequestHeader("Authorization") String authHeader
    ) {
        String token = extractToken(authHeader);
        return userService.deleteUser(id, token);
    }

}
