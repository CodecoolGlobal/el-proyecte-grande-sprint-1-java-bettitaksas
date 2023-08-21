package com.fridgemaster.demo.service.impl;

import com.fridgemaster.demo.entity.Fridge;
import com.fridgemaster.demo.entity.Item;
import com.fridgemaster.demo.entity.ItemType;
import com.fridgemaster.demo.entity.Recipe;
import com.fridgemaster.demo.repository.FridgeRepository;
import com.fridgemaster.demo.repository.RecipeRepository;
import com.fridgemaster.demo.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.NoSuchObjectException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final FridgeRepository fridgeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository, FridgeRepository fridgeRepository) {
        this.recipeRepository = recipeRepository;
        this.fridgeRepository = fridgeRepository;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe saveRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
        return recipe;
    }

    @Override
    public Recipe updateRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
        return recipe;
    }

    @Override
    public void deleteRecipe(Long recipeId) {
        recipeRepository.deleteById(recipeId);
    }

    @Override
    public Recipe getRecipeUsingWorstConditionItem(Long fridgeId) throws NoSuchObjectException {
        Optional<Fridge> optionalFridge = fridgeRepository.findById(fridgeId);
        if (optionalFridge.isPresent()){
            List<Item> items = optionalFridge.get().getItems();

            Optional<Item> worstConditionItem = items.stream()
                    .min(Comparator.comparing(Item::getExpirationDate));

            ItemType type;
            if (worstConditionItem.isPresent()){
                type = worstConditionItem.get().getItemType();
                return recipeRepository.findAll().stream()
                        .filter(recipe -> recipe.getIngredients().stream()
                                .anyMatch(ingredient -> ingredient.getItemType().equals(type)))
                        .findAny().get();
            } return null;

        } throw new NoSuchObjectException("There is no fridge with this id.");
    }
}
