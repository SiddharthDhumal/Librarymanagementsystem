package com.springbootproject.Librarymanagementsystem.service;


import com.springbootproject.Librarymanagementsystem.model.User;
import com.springbootproject.Librarymanagementsystem.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

}
