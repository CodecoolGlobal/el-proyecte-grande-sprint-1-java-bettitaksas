package com.fridgemaster.demo.service;
import com.fridgemaster.demo.dto.UserDTO;
import com.fridgemaster.demo.model.Fridge;
import com.fridgemaster.demo.model.User;
import com.fridgemaster.demo.repository.FridgeRepository;
import com.fridgemaster.demo.repository.UserRepository;
import com.fridgemaster.demo.security.Role;
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
        user.setRole(Role.User);
        Fridge fridge = new Fridge();
        fridge.setUser(user);
        fridgeRepository.save(fridge);
        user.setFridge(fridge);
        userRepository.save(user);
    }

    public Long loginUser(User user){
        User loggedInUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        return fridgeRepository.findFridgeByUser(loggedInUser).getId();
    }
    public Long loginUser(UserDTO userDTO){
        User loggedInUser = userRepository.findByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword());
        return fridgeRepository.findFridgeByUser(loggedInUser).getId();
    }

    public UserDTO findByLogin(String login) {
        User user = userRepository.findByUsername(login);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }
}
