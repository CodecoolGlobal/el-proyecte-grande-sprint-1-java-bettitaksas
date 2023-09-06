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
        System.out.println("HEREEEEEEE");
        userService.registerUser(user);
    }

    //login user sends back the id of their fridge for now
    @PutMapping("/login")
    public Long loginUser(@RequestBody User user){
        System.out.println("HELLO");
        return userService.loginUser(user);
    }
}
