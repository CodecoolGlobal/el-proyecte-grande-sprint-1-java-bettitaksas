package com.fridgemaster.demo.service;
import com.fridgemaster.demo.model.Fridge;
import com.fridgemaster.demo.model.User;
import com.fridgemaster.demo.repository.FridgeRepository;
import com.fridgemaster.demo.model.Item;
import com.fridgemaster.demo.model.Recipe;
import com.fridgemaster.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FridgeService{

   private final FridgeRepository fridgeRepository;
   private final UserRepository userRepository;
    @Autowired
    public FridgeService(FridgeRepository fridgeRepository, UserRepository userRepository) {
        this.fridgeRepository = fridgeRepository;
        this.userRepository = userRepository;
    }

    public List<Fridge> getFridges(){
        return fridgeRepository.findAll();
    }

    public List<Item> getFridgeContents(Long userId){
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            Fridge fridge = fridgeRepository.getFridgeByUser(user.get());
            return fridge.getFridgeItems();
        }
        else {
            return null;
        }
    }

    public void addItem(Long fridgeId, Item item) {
    }

    public void deleteItem(Long fridgeId, Long itemId) {
        /*fridgeRepository.deleteItemFromFridge(fridgeId, itemId); */
    }
    public void useRecipe(Long fridgeId, Recipe recipe) {

    }
    public void startNewFridge(){
        /*fridgeRepository.startNewFridge();*/
    }
}
