package com.calorease.calorease.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.calorease.calorease.dto.UserDTO;
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
    private final UserRepository userRepository; // Add this

    @Autowired
    public UserController(UserService userService, UserRepository userRepository){
        this.userService = userService;
        this.userRepository = userRepository;
    }
    
    @PostMapping("/users")
    public UserDTO registerUser(@RequestBody @Valid UserDTO userDTO){
        User user = userService.registerUser(User.from(userDTO));
        return UserDTO.from(user);
    }
    
    @GetMapping("/")
    public String home() {
    	return "Welcome to the Calorease API!";
    }

    @GetMapping("/users")
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers().stream().map(UserDTO::from).collect(Collectors.toList());
    }

    @GetMapping("/users/{email}")
    public UserDTO getUserByEmail(@PathVariable String email){
        User user = userService.getUserByEmail(email);
        return UserDTO.from(user);
    }
    
    //adding the method to verify UserRepo and database integration
    @GetMapping("/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
   
}


