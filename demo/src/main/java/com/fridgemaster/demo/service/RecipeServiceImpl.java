package com.fridgemaster.demo.service;

import com.fridgemaster.demo.model.Recipe;
import com.fridgemaster.demo.model.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RecipeServiceImpl implements RecipeService {
    RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.getAllRecipes();
    }

    @Override
    public List<Recipe> getRecipesMatchingWithItem(UUID itemId) {
        return recipeRepository.getRecipesMatchingWithItem(itemId);
    }
}
