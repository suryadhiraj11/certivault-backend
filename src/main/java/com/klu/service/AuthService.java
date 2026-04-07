package com.klu.service;

import com.klu.entity.User;
import com.klu.repository.UserRepository;
import com.klu.security.JwtUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepo;

    public User register(User user) {
        return userRepo.save(user);
    }

    public Map<String, Object> login(String email, String password) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        if (user.isDisabled()) {
            throw new RuntimeException("Account disabled");
        }

        String token = JwtUtil.generateToken(user.getEmail());

        Map<String, Object> res = new HashMap<>();
        res.put("user", user);
        res.put("token", token);

        return res;
    }

    // 🔥 ADDED METHOD
    
    
    public User updateProfile(Long id, User updated) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (updated.getName() != null) {
            user.setName(updated.getName());
        }

        if (updated.getProfilePic() != null) {
            user.setProfilePic(updated.getProfilePic());
        }

        return userRepo.save(user);
    }
    public User toggleUserStatus(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        System.out.println("Before toggle: " + user.isDisabled()); // DEBUG

        user.setDisabled(!user.isDisabled());

        System.out.println("After toggle: " + user.isDisabled()); // DEBUG

        return userRepo.save(user);
    }
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}