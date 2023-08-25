package com.fridgemaster.demo.service;

import com.fridgemaster.demo.model.Fridge;
import com.fridgemaster.demo.model.Item;
import com.fridgemaster.demo.model.ItemType;
import com.fridgemaster.demo.model.Recipe;
import com.fridgemaster.demo.repository.FridgeRepository;
import com.fridgemaster.demo.repository.ItemRepository;
import com.fridgemaster.demo.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.NoSuchObjectException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecipeService{
    private final RecipeRepository recipeRepository;
    private final FridgeRepository fridgeRepository;

    private final ItemRepository itemRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, FridgeRepository fridgeRepository, ItemRepository itemRepository) {
        this.recipeRepository = recipeRepository;
        this.fridgeRepository = fridgeRepository;
        this.itemRepository = itemRepository;
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

    public Recipe getRecipeUsingWorstConditionItem(Long fridgeId) throws NoSuchObjectException {
        Optional<Fridge> optionalFridge = fridgeRepository.findById(fridgeId);

        if (optionalFridge.isPresent()) {
            List<Item> items = optionalFridge.get().getFridgeItems();

            Optional<Item> worstConditionItem = items.stream()
                    .min(Comparator.comparing(Item::getExpirationDate));

            if (worstConditionItem.isPresent()) {
                ItemType type = worstConditionItem.get().getType();
                return recipeRepository.findRecipesByItemType(type).stream().findAny().orElse(null);
            }
        } else {
            throw new NoSuchObjectException("There is no fridge with this id.");
        }

        return null;
    }

    public Recipe recommendRecipe(Long fridgeId) throws NoSuchObjectException {
        return getRecipeUsingWorstConditionItem(fridgeId);
    }
    public List<Recipe> recommendRecipePrototype(Long fridgeId) {
        List<Recipe> recipes = recipeRepository.findAll();
        List<Item> fridgeContent = fridgeRepository.findById(fridgeId).get().getFridgeItems();
        recipes = orderByExpiringItems(recipes, fridgeContent, RecommendedRecipeNumConst.RECOMMENDED_RECIPE_COUNT);
        recipes = orderByRequiredItemCount(recipes, fridgeContent, RecommendedRecipeNumConst.RECOMMENDED_RECIPE_COUNT);



        return recipes;
    }
    private List<Recipe> orderByRequiredItemCount(List<Recipe> recipes, List<Item>items, int numberOfRecipesNeeded){
        List<Recipe> orderedRecipes = new ArrayList<>(recipes);
        Collections.sort(orderedRecipes, (o1,o2)-> {
            int requiredItemCountO1 = 0;
            int requiredItemCountO2 = 0;
            for(Item item : o1.getIngredients()){
                if(!items.contains(item)){
                    requiredItemCountO1++;
                }
            }
            for(Item item : o2.getIngredients()){
                if(!items.contains(item)){
                    requiredItemCountO2++;
                }
            }

            return requiredItemCountO1 - requiredItemCountO2;
        });
        return orderedRecipes.stream().limit(numberOfRecipesNeeded).collect(Collectors.toList());
    }

    private List<Recipe> orderByExpiringItems(List<Recipe> recipes, List<Item> items, int numberOfRecipesNeeded){
        List<Recipe> orderedRecipes = new ArrayList<>(recipes);
        List<Item> itemsSortedByExpiration = new ArrayList<>(items);
        itemsSortedByExpiration.sort(Comparator.comparing(Item::getExpirationDate));
        Collections.sort(orderedRecipes, (o1,o2)-> {
            int o1Score = 0;
            int o2Score = 0;
            for(int i = 0, score = itemsSortedByExpiration.size(); i < itemsSortedByExpiration.size(); i++ , score--){
                if(o1.getIngredients().contains(itemsSortedByExpiration.get(i))){
                    o1Score += score;
                }
                if(o2.getIngredients().contains(itemsSortedByExpiration.get(i))){
                    o2Score += score;
                }
            }
            return  o2Score - o1Score;
        });
        return orderedRecipes.stream().limit(numberOfRecipesNeeded).collect(Collectors.toList());
    }
}
