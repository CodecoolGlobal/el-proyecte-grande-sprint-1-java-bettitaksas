package com.fridgemaster.demo.model;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class RecipeRepository {
    List<Recipe> recipes;

    public RecipeRepository(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<Recipe> getAllRecipes() {
        return recipes;
    }

    public List<Recipe> getRecipesMatchingWithItem(UUID itemId) {
        return recipes.stream()
                .filter(recipe -> recipe.getIngredients().stream().anyMatch(ingredient -> ingredient.getId().equals(itemId)))
                .collect(Collectors.toList());
    }
}
