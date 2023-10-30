package com.fridgemaster.service;

import com.fridgemaster.model.User;
import com.fridgemaster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FridgeUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public FridgeUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
      //  UserDetails userDetails = new UserDetailsImpl(user.getUsername(), user.getPassword());
        return userDetails;
    }
}
