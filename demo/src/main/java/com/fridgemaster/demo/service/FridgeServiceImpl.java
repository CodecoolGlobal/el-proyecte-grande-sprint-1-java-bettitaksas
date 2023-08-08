package com.fridgemaster.demo.service;
import com.fridgemaster.demo.model.FridgeRepository;
import com.fridgemaster.demo.model.Item;
import com.fridgemaster.demo.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FridgeServiceImpl implements FridgeService{

    FridgeRepository fridgeRepository;
    @Autowired
    public FridgeServiceImpl(FridgeRepository fridgeRepository) {
        this.fridgeRepository = fridgeRepository;
    }

    @Override
    public List<Item> getFridgeContents(UUID id) {
        return fridgeRepository.getFridgeItem(id);
    }

    @Override
    public void addItem(UUID fridgeId, Item item) {
         fridgeRepository.addItemToFridge(fridgeId, item);
    }

    @Override
    public void deleteItem(UUID fridgeId, Item item) {
        fridgeRepository.deleteItemFromFridge(fridgeId, item);
    }

    @Override
    public void useRecipe(UUID fridgeId, Recipe recipe) {
        for(Item ingredient : recipe.getIngredients()){
            fridgeRepository.deleteItemFromFridge(fridgeId, ingredient);
        }
    }

    @Override
    public void deleteItem(UUID fridgeId, UUID itemId) {

    }


}
