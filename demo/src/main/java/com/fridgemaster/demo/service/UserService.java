package com.fridgemaster.demo.service;
import com.fridgemaster.demo.model.Fridge;
import com.fridgemaster.demo.model.User;
import com.fridgemaster.demo.repository.FridgeRepository;
import com.fridgemaster.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    UserRepository userRepository;
    FridgeRepository fridgeRepository;


    public UserService(UserRepository userRepository, FridgeRepository fridgeRepository) {
        this.userRepository = userRepository;
        this.fridgeRepository = fridgeRepository;
    }

    @Transactional
    public void registerUser(User user) {
        Fridge fridge = new Fridge();
        fridge.setUser(user);
        fridgeRepository.save(fridge);
        user.setFridge(fridge);
        userRepository.save(user);
    }
}
