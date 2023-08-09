package com.fridgemaster.demo.model;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class RecipeRepository {
    List<Recipe> recipes;

    public RecipeRepository() {
        this.recipes = new ArrayList<>();
    }

    public List<Recipe> getAllRecipes() {
        return recipes;
    }

    public List<Recipe> getRecipesMatchingWithItem(UUID itemId) {
        return recipes.stream()
                .filter(recipe -> recipe.getIngredients().stream().anyMatch(ingredient -> ingredient.getId().equals(itemId)))
                .collect(Collectors.toList());
    }

    @PostConstruct
    private void init(){
        Recipe recipe = new Recipe();

        recipes.add(recipe);
    }
}
