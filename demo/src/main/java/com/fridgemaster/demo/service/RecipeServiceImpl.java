package com.fridgemaster.demo.service;

import com.fridgemaster.demo.model.Item;
import com.fridgemaster.demo.model.ItemType;
import com.fridgemaster.demo.model.Recipe;
import com.fridgemaster.demo.model.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

//TODO query methods need to be in repository class
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
    public Recipe getRecipeUsingWorstConditionItem(UUID fridgeId) {
        //return recipeRepository.getRecipesMatchingWithItem(itemId);
        List<Item> items = fridgeService.getFridgeItems(fridgeId);

        Optional<Item> worstConditionItem = items.stream()
                .min(Comparator.comparing(Item::getExpirationDate));

        ItemType type;
        if (worstConditionItem.isPresent()){
            type = worstConditionItem.get().getType();
            return recipeRepository.getAllRecipes().stream()
                    .filter(recipe -> recipe.getIngredients().stream()
                            .anyMatch(ingredient -> ingredient.getType().equals(type)))
                    .findAny().get();
        } return null;
    }

    @Override
    public Recipe recommendRecipe(UUID fridgeId) {
        List<Item> items = fridgeService.getFridgeItems(fridgeId);
        List<Recipe> recipeList = new ArrayList<>(recipeRepository.getAllRecipes());
        List<Item> itemsClone = new ArrayList<>(items);
       /* Collections.sort(recipeList, (o1, o2) -> o2.getIngredients().stream().filter(items::contains).collect(Collectors.toList()).size() -
                o1.getIngredients().stream().filter(items::contains).collect(Collectors.toList()).size()); */
        Collections.sort(recipeList, (o2, o1) ->
                itemsClone.stream().filter(item -> o2.getIngredients().contains(item)).collect(Collectors.toList()).size() -
                        itemsClone.stream().filter(item -> o1.getIngredients().contains(item)).collect(Collectors.toList()).size());

        return recipeList.get(0);
    }

}
