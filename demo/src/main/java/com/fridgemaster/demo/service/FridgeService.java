package com.fridgemaster.demo.service;

import java.util.UUID;

public interface FridgeService {

    List<Item> getFridgeContents(UUID fridgeId);

    boolean addItem(UUID fridgeId,Item item);
    boolean consumeItem(UUID fridgeId, Item item);
    boolean useRecipe(Recipe recipe);
}
