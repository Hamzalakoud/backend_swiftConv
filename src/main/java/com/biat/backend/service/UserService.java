package com.biat.backend.service;

import com.biat.backend.dto.AuthResponse;
import com.biat.backend.dto.RegisterRequest;
import com.biat.backend.model.User;
import com.biat.backend.repository.UserRepository;
import com.biat.backend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String register(RegisterRequest request) {
        try {
            User user = new User();
            user.setFirstname(request.getFirstname());
            user.setLastname(request.getLastname());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(request.getRole());
            user.setStatus(request.getStatus() != null ? request.getStatus() : true); // default true
            user.setCreationDate(LocalDateTime.now());
            user.setLastUpdateDate(LocalDateTime.now());

            userRepository.save(user);

            return "User registered successfully";
        } catch (Exception e) {
            throw new RuntimeException("Registration failed: " + e.getMessage(), e);
        }
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }


    public AuthResponse updateUser(Long id, RegisterRequest request, String token) {
        try {
            if (!jwtService.isTokenValid(token)) {
                throw new RuntimeException("Invalid or expired token");
            }

            String emailFromToken = jwtService.extractUsername(token);
            String roleFromToken = jwtService.extractRole(token);

            User user = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            System.out.println("emailFromToken: " + emailFromToken);
            System.out.println("roleFromToken: " + roleFromToken);
            System.out.println("user.getEmail(): " + user.getEmail());

            // Authorization: allow if same user OR admin
            if (!user.getEmail().equals(emailFromToken) && !"admin".equalsIgnoreCase(roleFromToken)) {
                throw new RuntimeException("Unauthorized to update this user");
                
            }

            if (request.getFirstname() != null) user.setFirstname(request.getFirstname());
            if (request.getLastname() != null) user.setLastname(request.getLastname());
            if (request.getEmail() != null) user.setEmail(request.getEmail());
            if (request.getPassword() != null && !request.getPassword().isEmpty())
                user.setPassword(passwordEncoder.encode(request.getPassword()));
            if (request.getRole() != null) user.setRole(request.getRole());
            if (request.getStatus() != null) user.setStatus(request.getStatus());

            user.setLastUpdateDate(LocalDateTime.now());

            userRepository.save(user);

            String newToken = jwtService.generateToken(user);
            return new AuthResponse(newToken, user.getEmail(), user.getRole());

        } catch (Exception e) {
            throw new RuntimeException("Update failed: " + e.getMessage(), e);
        }
    }

    public String deleteUser(Long id, String token) {
        try {
            if (!jwtService.isTokenValid(token)) {
                throw new RuntimeException("Invalid or expired token");
            }

            String emailFromToken = jwtService.extractUsername(token);
            String roleFromToken = jwtService.extractRole(token);

            User user = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Authorization: allow if same user OR admin
            if (!user.getEmail().equals(emailFromToken) && !"admin".equalsIgnoreCase(roleFromToken)) {
                throw new RuntimeException("Unauthorized to delete this user");
            }

            userRepository.delete(user);
            return "User deleted successfully: ID = " + id;

        } catch (Exception e) {
            throw new RuntimeException("Deletion failed: " + e.getMessage(), e);
        }
    }

    public Long getUserIdByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getId();
    }

    public List<User> getAllUsers(String token) {
        if (!jwtService.isTokenValid(token)) {
            throw new RuntimeException("Invalid or expired token");
        }

        // Optionally, extract role or user info from token for authorization
        String roleFromToken = jwtService.extractRole(token);
        if (!"admin".equalsIgnoreCase(roleFromToken)) {
            throw new RuntimeException("Unauthorized access: only admins can get all users");
        }

        return userRepository.findAll();
    }

}
