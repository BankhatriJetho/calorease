package com.calorease.calorease.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.calorease.calorease.repository.UserRepository;

import jakarta.transaction.Transactional;

import java.util.Optional;
import java.util.List;
import com.calorease.calorease.entity.User;

@Service
@Transactional 
public class UserService {
    
    private final UserRepository userRepository;
    
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
    	System.out.println("Registering user: " + user);
        return userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
        		.orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}