package com.fridgemaster.controller;

import com.fridgemaster.model.Recipe;
import com.fridgemaster.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.rmi.NoSuchObjectException;
import java.util.List;

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

    @GetMapping("/{fridgeId}")
    public List<Recipe> testRecommendationPrototype(@PathVariable Long fridgeId){
        return recipeService.recommendRecipePrototype(fridgeId);
    }
    @GetMapping("/placeholder/{recipeId}")
    public Recipe getRecipe(@PathVariable Long recipeId){
        return recipeService.getRecipeById(recipeId);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/recommendation/{fridgeId}")
    public Recipe recommendRecipe(@PathVariable Long fridgeId) throws NoSuchObjectException {
        //return recipeService.recommendRecipe(fridgeId);
        return recipeService.getRecipeUsingWorstConditionItem(fridgeId);
    }
}
