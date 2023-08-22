package com.fridgemaster.demo.service;

import com.fridgemaster.demo.model.Fridge;
import com.fridgemaster.demo.model.Item;
import com.fridgemaster.demo.model.ItemType;
import com.fridgemaster.demo.model.Recipe;
import com.fridgemaster.demo.repository.FridgeRepository;
import com.fridgemaster.demo.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.NoSuchObjectException;
import java.util.*;

@Service
public class RecipeService{
    private final RecipeRepository recipeRepository;
    private final FridgeRepository fridgeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, FridgeRepository fridgeRepository) {
        this.recipeRepository = recipeRepository;
        this.fridgeRepository = fridgeRepository;
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe saveRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
        return recipe;
    }

    public Recipe updateRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
        return recipe;
    }

    public void deleteRecipe(Long recipeId) {
        recipeRepository.deleteById(recipeId);
    }

/*    public Recipe getRecipeUsingWorstConditionItem(Long fridgeId) throws NoSuchObjectException {
        Optional<Fridge> optionalFridge = fridgeRepository.findById(fridgeId);
        if (optionalFridge.isPresent()){
            List<Item> items = optionalFridge.get().getFridgeItems();

            Optional<Item> worstConditionItem = items.stream()
                    .min(Comparator.comparing(Item::getExpirationDate));

            ItemType type;
            if (worstConditionItem.isPresent()){
                type = worstConditionItem.get().getType();
                return recipeRepository.findAll().stream()
                        .filter(recipe -> recipe.getIngredients().stream()
                                .anyMatch(ingredient -> ingredient.getType().equals(type)))
                        .findAny().get();
            } return null;

        } throw new NoSuchObjectException("There is no fridge with this id.");
    }*/

    public Recipe getRecipeUsingWorstConditionItem(Long fridgeId) throws NoSuchObjectException {
        Optional<Fridge> optionalFridge = fridgeRepository.findById(fridgeId);

        if (optionalFridge.isPresent()) {
            List<Item> items = optionalFridge.get().getFridgeItems();

            Optional<Item> worstConditionItem = items.stream()
                    .min(Comparator.comparing(Item::getExpirationDate));

            if (worstConditionItem.isPresent()) {
                ItemType type = worstConditionItem.get().getType();
                return recipeRepository.findRecipeByItemType(type)
                        .orElse(null);
            }
        } else {
            throw new NoSuchObjectException("There is no fridge with this id.");
        }

        return null;
    }

    public Recipe recommendRecipe(Long fridgeId) throws NoSuchObjectException {
        return getRecipeUsingWorstConditionItem(fridgeId);
    }
}
