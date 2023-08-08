package com.fridgemaster.demo.service;

import com.fridgemaster.demo.model.Item;
import com.fridgemaster.demo.model.Recipe;

import java.util.List;
import java.util.UUID;

public interface FridgeService {

    List<Item> getFridgeContents(UUID fridgeId);

    boolean addItem(UUID fridgeId, Item item);
    boolean consumeItem(UUID fridgeId, Item item);
    boolean useRecipe(Recipe recipe);

    void deleteItem(UUID fridgeId, UUID itemId);
}
