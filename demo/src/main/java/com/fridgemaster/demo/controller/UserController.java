package com.fridgemaster.demo.controller;

import com.fridgemaster.demo.model.User;
import com.fridgemaster.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {
    UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping ("/register")
    public void registerUser(@RequestBody User user){
        userService.registerUser(user);
    }
}
