package com.fridgemaster.demo.model;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class RecipeRepository {
    List<Recipe> recipes;

    public RecipeRepository() {
        this.recipes = new ArrayList<>();
    }

    public List<Recipe> getAllRecipes() {
        return recipes;
    }

    public List<Recipe> getRecipesMatchingWithItem(UUID itemId) {
        return recipes.stream()
                .filter(recipe -> recipe.getIngredients().stream().anyMatch(ingredient -> ingredient.getId().equals(itemId)))
                .collect(Collectors.toList());
    }

    @PostConstruct
    private void init(){
        Recipe greekSalad = new Recipe("Greek Salad");
        greekSalad.addIngredient(new Item(ItemType.CHEESE));
        greekSalad.addIngredient(new Item(ItemType.TOMATO));
        greekSalad.addIngredient(new Item(ItemType.ONION));
        greekSalad.addIngredient(new Item(ItemType.CUCUMBER));
        greekSalad.setDescription("Slice some cucumbers, tomatoes, red onion. Mix with Kalamata olives. Toss with crumbled feta cheese, drizzle with extra virgin olive oil, and finish with a sprinkle of dried oregano for a refreshing Mediterranean delight.");
        recipes.add(greekSalad);

        Recipe spaghettiBolognese = new Recipe("Spaghetti Bolognese");
        spaghettiBolognese.addIngredient(new Item(ItemType.GARLIC));
        spaghettiBolognese.addIngredient(new Item(ItemType.ONION));
        spaghettiBolognese.addIngredient(new Item(ItemType.TOMATO));
        spaghettiBolognese.addIngredient(new Item(ItemType.GROUND_MEAT));
        spaghettiBolognese.addIngredient(new Item(ItemType.CHEESE));
        spaghettiBolognese.addIngredient(new Item(ItemType.WINE));
        spaghettiBolognese.setDescription("Create a rich spaghetti Bolognese by browning a mix of ground beef and pork with finely chopped onion and minced garlic. Simmer the meat with tomato sauce, tomato paste, red wine, grated carrots, and chopped celery, then season with herbs and spices for a hearty and flavorful sauce.");
        recipes.add(spaghettiBolognese);

        Recipe creamyBroccoliSoup = new Recipe("Creamy Broccoli Soup");
        creamyBroccoliSoup.addIngredient(new Item(ItemType.ONION));
        creamyBroccoliSoup.addIngredient(new Item(ItemType.GARLIC));
        creamyBroccoliSoup.addIngredient(new Item(ItemType.BROCCOLI));
        creamyBroccoliSoup.addIngredient(new Item(ItemType.YOGURT));
        creamyBroccoliSoup.setDescription("Chop some onion, garlic, and broccoli. Blend with vegetable broth, then stir in plain yogurt for a luscious texture, resulting in a delightful balance of flavors and wholesome comfort.");
        recipes.add(creamyBroccoliSoup);

        Recipe crunchyPotatoChips = new Recipe("Crunchy Potato Chips");
        crunchyPotatoChips.addIngredient(new Item(ItemType.POTATO));
        crunchyPotatoChips.addIngredient(new Item(ItemType.BUTTER));
        crunchyPotatoChips.setDescription("Thinly slice and toss 2-3 large potatoes with a little butter, salt, and optional seasonings. Bake at 375°F (190°C) for 10-15 minutes per side until golden and crispy, then cool on a wire rack for homemade crunchy potato chips.");
        recipes.add(crunchyPotatoChips);

    }
}
