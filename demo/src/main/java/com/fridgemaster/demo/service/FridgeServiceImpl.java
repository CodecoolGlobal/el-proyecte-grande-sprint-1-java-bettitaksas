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
        return null;
        //fridgeRepository.getFridgeContent(id);
    }

    @Override
    public boolean addItem(UUID fridgeId, Item item) {
        return false;
              //  fridgeRepository.addItem(fridgeId, item);
    }

    @Override
    public boolean deleteItem(UUID fridgeId, Item item) {
        return false;
                //fridgeRepository.consumeItem(fridgeId, item);
    }

    @Override
    public boolean useRecipe(UUID fridgeId, Recipe recipe) {
        return false;
        // for(Item ingredient : recipe.ingredients()){
        // fridgeRepository.consumeItem(fridgeId, ingredient)
        // }
    }


}
