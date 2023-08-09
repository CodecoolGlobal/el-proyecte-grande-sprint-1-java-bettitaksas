package com.fridgemaster.demo.model;

import org.springframework.stereotype.Repository;

import java.rmi.NoSuchObjectException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class RecipeRepository {
    List<Recipe> recipes;

    public RecipeRepository(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<Recipe> getAllRecipes(){
        return recipes;
    }

    public Recipe getFirstRecipeMatchingWithItem(UUID itemId) throws NoSuchObjectException {
        Optional<Recipe> optionalRecipe = recipes.stream()
                .filter(recipe -> recipe.getIngredients().stream().anyMatch(ingredient -> ingredient.getId().equals(itemId)))
                .findFirst();

        if (optionalRecipe.isPresent()){
            return optionalRecipe.get();
        } throw new NoSuchObjectException("There is no recipe matching with the item.");
    }
}
