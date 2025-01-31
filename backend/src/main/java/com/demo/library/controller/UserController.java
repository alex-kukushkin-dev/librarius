package com.demo.library.controller;

import com.demo.library.entity.User;
import com.demo.library.exception.UserFriendlyException;
import com.demo.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .orElseThrow(() -> new UserFriendlyException("User not found with ID: " + id));
    }

    @PostMapping
    public User createUser(@RequestBody String username) {
        return userService.createUser(username);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User existingUser = userService.getUserById(id)
                .orElseThrow(() -> new UserFriendlyException("User not found with ID: " + id));
        existingUser.setUsername(updatedUser.getUsername());
        return userService.saveUser(existingUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}


