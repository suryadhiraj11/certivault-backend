package com.klu.controller;

import com.klu.repository.UserRepository;
import com.klu.entity.User;
import com.klu.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepo; // 🔥 needed for /me

    // ================= REGISTER =================
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody User user) {
        Map<String, Object> res = new HashMap<>();
        try {
            User savedUser = authService.register(user);

            res.put("user", savedUser);
            res.put("success", true);

        } catch (Exception e) {
            res.put("error", e.getMessage());
        }
        return res;
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User user) {
        Map<String, Object> res = new HashMap<>();

        try {
            return authService.login(user.getEmail(), user.getPassword());
        } catch (RuntimeException e) {
            res.put("error", e.getMessage()); // 🔥 send message instead of crash
            return res;
        }
    }

    // ================= 🔥 GET CURRENT USER =================
    @GetMapping("/me")
    public User getCurrentUser(HttpServletRequest request) {

        String email = (String) request.getAttribute("email");

        if (email == null) {
            throw new RuntimeException("Unauthorized: No token or invalid token");
        }

        return userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // ================= UPDATE PROFILE =================
    @PutMapping("/update-profile/{id}")
    public User updateProfile(@PathVariable Long id, @RequestBody User updated) {
        return authService.updateProfile(id, updated);
    }

    // ================= GET ALL USERS =================
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return authService.getAllUsers();
    }

    // ================= TOGGLE USER =================
    @PutMapping("/toggle/{id}")
    public User toggleUser(@PathVariable Long id) {
        return authService.toggleUserStatus(id);
    }
}