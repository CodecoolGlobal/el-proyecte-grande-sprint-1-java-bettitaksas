package com.fridgemaster.demo.service;

import com.fridgemaster.demo.model.Recipe;

import java.util.List;
import java.util.UUID;

public interface RecipeService {
    List<Recipe> getAllRecipes();

    Recipe getRecipeUsingWorstConditionItem(UUID fridgeId);

    Recipe recommendRecipe(UUID fridgeId);
}
