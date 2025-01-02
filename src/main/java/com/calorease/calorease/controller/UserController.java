package com.calorease.calorease.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.calorease.calorease.dto.UserDTO;
import com.calorease.calorease.dto.UserRegistrationDTO;
import com.calorease.calorease.service.UserService;

import jakarta.validation.Valid;

import com.calorease.calorease.entity.User;
import com.calorease.calorease.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController{
    private final UserService userService;
    private final UserRepository userRepository; 

    @Autowired
    public UserController(UserService userService, UserRepository userRepository){
        this.userService = userService;
        this.userRepository = userRepository;
    }
    
    // Endpoint for full registration with password (UserRegistrationDTO)
    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<String> registerUser(@RequestBody @Valid UserRegistrationDTO userDTO) {
        try {
            userService.registerUser(userDTO);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error registering user: " + e.getMessage());
        }
    }
    
    // Endpoint for lightweight registration (UserDTO)
    @PostMapping(value = "/register-user", consumes = "application/json")
    public ResponseEntity<String> registerUser(@RequestBody @Valid UserDTO userDTO) {
        try {
            User user = userService.registerUser(User.from(userDTO));
            return ResponseEntity.ok("User registered: " + user.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error registering user: " + e.getMessage());
        }
    }

    @GetMapping("/")
    public String home() {
    	return "Welcome to the Calorease API!";
    }

    @GetMapping
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers().stream().map(UserDTO::from).collect(Collectors.toList());
    }

    @GetMapping("/{email}")
    public UserDTO getUserByEmail(@PathVariable String email){
        User user = userService.getUserByEmail(email);
        return UserDTO.from(user);
    }
    
    @GetMapping("/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
