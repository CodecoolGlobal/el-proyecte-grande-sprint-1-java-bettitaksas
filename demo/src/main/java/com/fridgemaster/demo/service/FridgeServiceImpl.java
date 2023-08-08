package com.fridgemaster.demo.service;

import com.fridgemaster.demo.model.Item;
import com.fridgemaster.demo.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FridgeServiceImpl implements FridgeService{
    @Override
    public List<Item> getFridgeContents(UUID id) {
        return null;
    }

    @Override
    public boolean addItem(UUID fridgeId, Item item) {
        return false;
    }

    @Override
    public boolean consumeItem(UUID fridgeId, Item item) {
        return false;
    }

    @Override
    public boolean useRecipe(Recipe recipe) {
        return false;
    }

    @Override
    public void deleteItem(UUID fridgeId, UUID itemId) {

    }


}
