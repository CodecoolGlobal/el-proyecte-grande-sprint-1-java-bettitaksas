package com.fridgemaster.demo.service;

import com.fridgemaster.demo.model.Item;
import com.fridgemaster.demo.model.Recipe;
import com.fridgemaster.demo.model.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.NoSuchObjectException;
import java.util.UUID;

@Service
public class RecipeServiceImpl implements RecipeService{
    RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Recipe getFirstRecipeMatchingWithItem(UUID itemId) throws NoSuchObjectException {
        return recipeRepository.getFirstRecipeMatchingWithItem(itemId);
    }
}
