package com.fridgemaster.demo.service;

import com.fridgemaster.demo.model.Fridge;
import com.fridgemaster.demo.model.Item;
import com.fridgemaster.demo.model.Recipe;

import java.util.List;
import java.util.UUID;

public interface FridgeService {

    List<Fridge> getFridges();

    List<Item> getFridgeItems(UUID fridgeId);
    void addItem(UUID fridgeId,Item item);
    void deleteItem(UUID fridgeId, UUID itemId);
    void useRecipe(UUID fridgeId, Recipe recipe);
}
