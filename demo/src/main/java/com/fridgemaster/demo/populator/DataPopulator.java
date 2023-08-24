package com.fridgemaster.demo.populator;


import com.fridgemaster.demo.model.Fridge;
import com.fridgemaster.demo.model.Item;
import com.fridgemaster.demo.model.ItemType;
import com.fridgemaster.demo.model.Recipe;
import com.fridgemaster.demo.repository.FridgeRepository;
import com.fridgemaster.demo.repository.ItemRepository;
import com.fridgemaster.demo.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DataPopulator implements ApplicationRunner {

    private final FridgeRepository fridgeRepository;
    private final ItemRepository itemRepository;
    private final RecipeRepository recipeRepository;

    @Autowired
    public DataPopulator(FridgeRepository fridgeRepository, ItemRepository itemRepository, RecipeRepository recipeRepository) {
        this.fridgeRepository = fridgeRepository;
        this.itemRepository = itemRepository;
        this.recipeRepository = recipeRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (fridgeRepository.count() == 0 || recipeRepository.count() == 0) {
            populateData();
        }
    }

    private void populateData() {
        Item cheese = new Item(ItemType.CHEESE, LocalDate.of(2023, 12, 1));
        Item ground_meet = new Item(ItemType.GROUND_MEAT, LocalDate.of(2024, 2, 10));
        Item potato = new Item(ItemType.POTATO, LocalDate.of(2024, 1, 25));
        Item tomato = new Item(ItemType.TOMATO, LocalDate.of(2024, 3, 3));
        Item onion = new Item(ItemType.ONION, LocalDate.of(2023, 12, 21));
        Item cucumber = new Item(ItemType.CUCUMBER, LocalDate.of(2023, 11, 1));
        Item garlic = new Item(ItemType.GARLIC, LocalDate.of(2023, 11, 1));
        Item wine = new Item(ItemType.WINE, LocalDate.of(2023, 11, 1));
        // ... add more items
        itemRepository.saveAll(Arrays.asList(cheese, ground_meet, potato, tomato, onion, cucumber, garlic, wine));

        Fridge fridge1 = new Fridge();
        fridgeRepository.save(fridge1);
        fridge1.addItemToFridge(cheese);
        fridge1.addItemToFridge(potato);
        fridgeRepository.save(fridge1);

        Recipe greekSalad = new Recipe("Greek Salad", "Slice some cucumbers, tomatoes, red onion. Mix with olives and feta cheese, drizzle with extra virgin olive oil, and use dried oregano for a refreshing Mediterranean delight.");
        greekSalad.addIngredient(new Item(ItemType.CHEESE));
        greekSalad.addIngredient(new Item(ItemType.TOMATO));
        greekSalad.addIngredient(new Item(ItemType.ONION));
        greekSalad.addIngredient(new Item(ItemType.CUCUMBER));
        // ... add more ingredients
        recipeRepository.save(greekSalad);

        Recipe spaghettiBolognese = new Recipe("Spaghetti Bolognese", "Brow a mix of ground beef and pork with chopped onion and garlic. Use tomato sauce, tomato paste, red wine, then add spices for flavorful sauce.");
        spaghettiBolognese.addIngredient(new Item(ItemType.ONION));
        spaghettiBolognese.addIngredient(new Item(ItemType.GARLIC));
        spaghettiBolognese.addIngredient(new Item(ItemType.WINE));
        spaghettiBolognese.addIngredient(new Item(ItemType.TOMATO));
        spaghettiBolognese.addIngredient(new Item(ItemType.GROUND_MEAT));
        // ... add more ingredients
        recipeRepository.save(spaghettiBolognese);

        // ... add more recipes

      /*  Recipe boringSoup = new Recipe("boring soup", "boring stuff");
        boringSoup.addIngredient(new Item(ItemType.KETCHUP));
        boringSoup.addIngredient(new Item(ItemType.BUTTER));
        recipeRepository.save(boringSoup);
        Recipe test01 = new Recipe("test", "test test");
        test01.addIngredient(new Item(ItemType.EGG));
        test01.addIngredient(new Item(ItemType.CARROT));
        recipeRepository.save(test01);
        Recipe testTonsOfStuff = new Recipe("testtt", "test");
        testTonsOfStuff.addIngredient(new Item(ItemType.CARROT));
        testTonsOfStuff.addIngredient(new Item(ItemType.KETCHUP));
        testTonsOfStuff.addIngredient(new Item(ItemType.CHICKEN));
        testTonsOfStuff.addIngredient(new Item(ItemType.BROCCOLI));
        testTonsOfStuff.addIngredient(new Item(ItemType.GROUND_MEAT));
        testTonsOfStuff.addIngredient(new Item(ItemType.TOFU));
        recipeRepository.save(testTonsOfStuff);
        Recipe test02 = new Recipe("1", "");
        test02.addIngredient(new Item(ItemType.CHICKEN));
        recipeRepository.save(test02);
        Recipe test03 = new Recipe("","");
        test03.addIngredient(new Item(ItemType.TOFU));
        recipeRepository.save(test03); */
    }
}
