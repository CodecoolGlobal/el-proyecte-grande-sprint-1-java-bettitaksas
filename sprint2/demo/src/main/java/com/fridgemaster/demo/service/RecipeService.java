package com.fridgemaster.demo.service;

import com.fridgemaster.demo.entity.Recipe;

import java.rmi.NoSuchObjectException;
import java.util.List;

public interface RecipeService {
    List<Recipe> getAllRecipes();
    Recipe saveRecipe(Recipe recipe);
    Recipe getRecipeUsingWorstConditionItem(Long fridgeId) throws NoSuchObjectException;
    Recipe updateRecipe(Recipe recipe);
    void deleteRecipe(Long recipeId);
}
