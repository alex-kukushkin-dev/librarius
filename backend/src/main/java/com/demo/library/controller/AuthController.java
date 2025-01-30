package com.demo.library.controller;

import com.demo.library.entity.User;
import com.demo.library.request.LoginRequest;
import com.demo.library.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/login")
    public User login(@RequestBody LoginRequest request) {
        var existing = userService.findByUsername(request.getUsername());
        return existing.orElseGet(() -> userService.createUser(request.getUsername()));
    }
}
