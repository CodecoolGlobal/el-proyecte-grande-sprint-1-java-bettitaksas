package com.fridgemaster.demo.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FridgeServiceImpl implements FridgeService{
    @Override
    public List<Item> getFridgeContents(UUID id) {
        return null;
    }

    @Override
    public boolean addItem(Item item) {
        return false;
    }

    @Override
    public boolean consumeItem(Item item) {
        return false;
    }

    @Override
    public boolean useRecipe(Recipe recipe) {
        return false;
    }


}
