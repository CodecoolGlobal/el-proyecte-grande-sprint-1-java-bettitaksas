package com.fridgemaster.demo.service;

import com.fridgemaster.demo.model.User;
import com.fridgemaster.demo.repository.UserRepository;
import com.fridgemaster.demo.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
        UserDetails userDetails = new UserDetailsImpl(user.getUsername(), user.getPassword());
        return userDetails;
    }
}
