package com.calorease.calorease.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.calorease.calorease.dto.UserDTO;
import com.calorease.calorease.service.UserService;
import com.calorease.calorease.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController{
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    
    @PostMapping("/users")
    public UserDTO registerUser(@RequestBody UserDTO userDTO){
        User user = userService.registerUser(User.from(userDTO));
        return UserDTO.from(user);
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
}


