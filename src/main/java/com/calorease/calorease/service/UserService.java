package com.calorease.calorease.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.calorease.calorease.repository.UserRepository;
import com.calorease.calorease.entity.Role;
import com.calorease.calorease.entity.User;
import com.calorease.calorease.repository.RoleRepository;
import com.calorease.calorease.entity.RoleEntity;
import com.calorease.calorease.dto.UserRegistrationDTO;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional 
public class UserService {
    
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Register User with DTO
    public User registerUser(UserRegistrationDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already in use.");
        }

        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        // Assign default USER role
        Set<RoleEntity> roles = new HashSet<>();
        RoleEntity userRole = roleRepository.findByName(Role.USER)
                                .orElseThrow(() -> new RuntimeException("Default USER role not found."));
        roles.add(userRole);
        
        user.setRoles(roles);

        return userRepository.save(user);
    }

    // Register User from Entity directly (Admin Use or other flows)
    public User registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already in use.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Assign default USER role
        Set<RoleEntity> roles = new HashSet<>();
        RoleEntity userRole = roleRepository.findByName(Role.USER)
                                .orElseThrow(() -> new RuntimeException("Default USER role not found."));
        roles.add(userRole);

        user.setRoles(roles);

        return userRepository.save(user);
    }

    // Get User by Email
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
        		.orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    // Get all Users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    @Transactional
    public void promoteUserToAdmin(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        RoleEntity adminRole = roleRepository.findByName(Role.ADMIN)
                .orElseThrow(() -> new RuntimeException("Admin role not found."));

        user.getRoles().add(adminRole);
        userRepository.save(user);
    }

    public List<User> getUsersByRole(Role role) {
        return userRepository.findByRoles_Name(role);
    }

}
