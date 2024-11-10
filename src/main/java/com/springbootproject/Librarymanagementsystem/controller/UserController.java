package com.springbootproject.Librarymanagementsystem.controller;


import com.springbootproject.Librarymanagementsystem.model.User;
import com.springbootproject.Librarymanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.findAllUsers();
    }

    @PostMapping
    public User addUser(@RequestBody User user){
        return userService.saveUser(user);
    }
}
