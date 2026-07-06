package com.kai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kai.constant.Role;
import com.kai.entity.User;
import com.kai.repository.UserRepository;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Backend is working bro 🚀";
    }
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/create-user")
    public User createUser() {
    	  User user = new User();
          user.setName("Kai");
          user.setPhone("0123456789");
          user.setEmail("kai@test.com");
          user.setPassword("123456");
          user.setRole(Role.USER);

        return userRepository.save(user);
    }
    
    
}