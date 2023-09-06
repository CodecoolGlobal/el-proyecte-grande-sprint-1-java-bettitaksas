package com.fridgemaster.demo.controller;

import com.fridgemaster.demo.model.Recipe;
import com.fridgemaster.demo.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.rmi.NoSuchObjectException;
import java.util.List;
import java.util.Set;
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

    @GetMapping("/{fridgeId}")
    public List<Recipe> testRecommendationPrototype(@PathVariable Long fridgeId){
        return recipeService.recommendRecipePrototype(fridgeId);
    }
    @GetMapping("/{recipeId}")
    public Recipe getRecipe(@RequestParam Long recipeId){
        return recipeService.getRecipeById(recipeId);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/recommendation/{fridgeId}")
    public Recipe recommendRecipe(@PathVariable Long fridgeId) throws NoSuchObjectException {
        //return recipeService.recommendRecipe(fridgeId);
        return recipeService.getRecipeUsingWorstConditionItem(fridgeId);
    }
}
