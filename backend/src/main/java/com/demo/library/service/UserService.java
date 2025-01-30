package com.demo.library.service;


import com.demo.library.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long userId);
    User saveUser(User user);
    User createUser(String username);
    Optional<User> findByUsername(String username);
    void deleteUser(Long userId);
}