package com.fridgemaster.demo.service;

import com.fridgemaster.demo.model.Recipe;
import com.fridgemaster.demo.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeService{
    RecipeRepository recipeRepository;
    FridgeService fridgeService;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, FridgeService fridgeService) {
        this.recipeRepository = recipeRepository;
        this.fridgeService = fridgeService;
    }


    public List<Recipe> getAllRecipes() {
        return null;
    }

    public Recipe getRecipeUsingWorstConditionItem(UUID fridgeId) {
        return null;
    }

    public Recipe recommendRecipe(UUID fridgeId) {
        return null;
    }
}
