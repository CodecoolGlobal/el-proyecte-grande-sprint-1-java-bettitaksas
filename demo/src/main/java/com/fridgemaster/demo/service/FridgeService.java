package com.fridgemaster.demo.service;
import com.fridgemaster.demo.model.Fridge;
import com.fridgemaster.demo.repository.FridgeRepository;
import com.fridgemaster.demo.model.Item;
import com.fridgemaster.demo.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FridgeService{

   private final FridgeRepository fridgeRepository;
    @Autowired
    public FridgeService(FridgeRepository fridgeRepository) {
        this.fridgeRepository = fridgeRepository;
    }

    public List<Fridge> getFridges(){
        return fridgeRepository.findAll();
    }

    public List<Item> getFridgeItems(Long id) {
        Optional<Fridge> fridge = fridgeRepository.findById(id);
        return fridge.get().getFridgeItems();
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
