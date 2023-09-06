package com.fridgemaster.demo.controller;

import com.fridgemaster.demo.dto.UserDTO;
import com.fridgemaster.demo.model.User;
import com.fridgemaster.demo.security.AuthProvider;
import com.fridgemaster.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {
    UserService userService;
    private final AuthProvider authProvider;
    @Autowired
    public UserController(UserService userService, AuthProvider authProvider) {
        this.userService = userService;
        this.authProvider = authProvider;
    }
    @PostMapping ("/register")
    public void registerUser(@RequestBody UserDTO userDTO){
        User user = new User();
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());
        user.setLogin(userDTO.getLogin());
        userService.registerUser(user);
    }
    @PutMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO) {
        Long fridgeId = userService.loginUser(userDTO);
        userDTO.setToken(authProvider.createToken(userDTO.getLogin()));
        userDTO.setFridgeId(fridgeId);
        return ResponseEntity.ok(userDTO);
    }
}
