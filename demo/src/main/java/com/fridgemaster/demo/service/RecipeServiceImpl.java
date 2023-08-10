package com.fridgemaster.demo.service;

import com.fridgemaster.demo.model.Item;
import com.fridgemaster.demo.model.Recipe;
import com.fridgemaster.demo.model.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {
    RecipeRepository recipeRepository;
    FridgeService fridgeService;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository, FridgeService fridgeService) {
        this.recipeRepository = recipeRepository;
        this.fridgeService = fridgeService;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.getAllRecipes();
    }

    @Override
    public List<Recipe> getRecipesMatchingWithItem(UUID itemId) {
        return recipeRepository.getRecipesMatchingWithItem(itemId);
    }

    @Override
    public Recipe recommendRecipe(UUID fridgeId) {
        List<Item> items = fridgeService.getFridgeItems(fridgeId);
        List<Recipe> recipeList = new ArrayList<>(recipeRepository.getAllRecipes());
        List<Item> itemsClone = new ArrayList<>(items);

        Collections.sort(recipeList, (o1,o2)-> {
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
        return recipeList.get(0);
    }

}
