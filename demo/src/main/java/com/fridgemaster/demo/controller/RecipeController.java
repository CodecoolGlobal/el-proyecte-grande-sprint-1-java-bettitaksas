package com.fridgemaster.demo.controller;

import com.fridgemaster.demo.model.Recipe;
import com.fridgemaster.demo.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.NoSuchObjectException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

/*    @GetMapping("/{itemId}")
    public Recipe getRecipesMatchingWithItem(@PathVariable UUID itemId) {
        return recipeService.getRecipeUsingWorstConditionItem(itemId);
    }*/
    @GetMapping("/recommendation/{fridgeId}")
    public Recipe recommendRecipe(@PathVariable Long fridgeId) throws NoSuchObjectException {
        //return recipeService.recommendRecipe(fridgeId);
        return recipeService.getRecipeUsingWorstConditionItem(fridgeId);
    }
}
