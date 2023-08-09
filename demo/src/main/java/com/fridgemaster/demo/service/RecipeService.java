package com.fridgemaster.demo.service;

import com.fridgemaster.demo.model.Item;
import com.fridgemaster.demo.model.Recipe;

import java.rmi.NoSuchObjectException;
import java.util.UUID;

public interface RecipeService {
    Recipe getFirstRecipeMatchingWithItem(UUID itemId) throws NoSuchObjectException;
}
