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
import java.util.NoSuchElementException;
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

    public List<Item> getFridgeItems(Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        if(user == null){
            throw new NoSuchElementException("No user with this ID");
        }else{
        Fridge fridge = fridgeRepository.findFridgeByUser(user);
        return fridge.getFridgeItems();
        }
    }

    public void addItem(Long fridgeId, Item item) {
        Optional<Fridge> fridge = fridgeRepository.findById(fridgeId);

        if(fridge.isPresent()){
        fridge.get().addItemToFridge(item);
        }
    }

    public void deleteItem(Long fridgeId, Long itemId) {
        Fridge fridge = fridgeRepository.findById(fridgeId).orElse(null);

        if(fridge == null){
            throw new NoSuchElementException("No fridge with this id");
        }else{
            fridge.deleteItemFromFridge(itemId);
        }
    }
    public void useRecipe(Long fridgeId, Recipe recipe) {

    }
    public void startNewFridge(){
        /*fridgeRepository.startNewFridge();*/
    }
}
