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

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecipeService{

    //TODO recipes should first be ordered by the number of spoiling
    // ingredients they use up and then further ordered by the amount of extra ingredients they need
    private RecipeRepository recipeRepository;
    private FridgeRepository fridgeRepository;
    private ItemRepository itemRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, FridgeRepository fridgeRepository, ItemRepository itemRepository) {
        this.recipeRepository = recipeRepository;
        this.fridgeRepository = fridgeRepository;
        this.itemRepository = itemRepository;
    }

    public RecipeService() {
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

    public Recipe getRecipeUsingWorstConditionItem(Long fridgeId) {
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
        }

        return null;
    }

    public Recipe recommendRecipe(Long fridgeId){
        return getRecipeUsingWorstConditionItem(fridgeId);
    }

    public Set<Recipe> recommendRecipePrototype(Long fridgeId) {
        List<Recipe> recipes = recipeRepository.findAll();
        List<Item> fridgeContent = itemRepository.findByFridge(fridgeRepository.findById(fridgeId).get());
        recipes = orderByExpiringItems(recipes, fridgeContent, RecommendationConstant.RECOMMENDED_NUMBER_OF_RECIPES);
        recipes = orderByRequiredItemCount(recipes, fridgeContent, RecommendationConstant.RECOMMENDED_NUMBER_OF_RECIPES);

        return (Set<Recipe>) recipes;
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
            for(int i = 0, score = itemsSortedByExpiration.size() - 1; i < itemsSortedByExpiration.size(); i++ , score--){
                if(o1.getIngredients().contains(itemsSortedByExpiration.get(i))){
                    o1Score += score;
                }
                if(o2.getIngredients().contains(itemsSortedByExpiration.get(i))){
                    o2Score += score;
                }
            }
            return o1Score - o2Score;
        });

        return orderedRecipes.stream().limit(numberOfRecipesNeeded).collect(Collectors.toList());
    }
}
