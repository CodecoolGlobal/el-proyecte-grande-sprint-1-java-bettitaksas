package com.fridgemaster.demo.initializer;

import com.fridgemaster.demo.entity.Fridge;
import com.fridgemaster.demo.entity.Item;
import com.fridgemaster.demo.entity.ItemType;
import com.fridgemaster.demo.entity.Recipe;
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
        if (fridgeRepository.count() == 0 && recipeRepository.count() == 0) {
            populateData();
        }
    }

    private void populateData() {
        Item cheese = new Item(1L, ItemType.CHEESE, LocalDate.of(2023, 12, 1));
        Item ground_meet = new Item(2L, ItemType.GROUND_MEAT, LocalDate.of(2024, 2, 10));
        Item potato = new Item(3L, ItemType.POTATO, LocalDate.of(2024, 1, 25));
        Item tomato = new Item(4L, ItemType.TOMATO, LocalDate.of(2024, 3, 3));
        Item onion = new Item(5L, ItemType.ONION, LocalDate.of(2023, 12, 21));
        Item cucumber = new Item(6L, ItemType.CUCUMBER, LocalDate.of(2023, 11, 1));
        Item garlic = new Item(7L, ItemType.GARLIC, LocalDate.of(2023, 11, 1));
        Item wine = new Item(8L, ItemType.WINE, LocalDate.of(2023, 11, 1));
        // ... add more items
        itemRepository.saveAll(Arrays.asList(cheese, ground_meet, potato, tomato, onion, cucumber, garlic, wine));

        Fridge fridge1 = new Fridge();
        fridgeRepository.save(fridge1);
        fridge1.addItem(cheese);
        fridge1.addItem(potato);
        fridgeRepository.save(fridge1);

        Recipe greekSalad = new Recipe(1L, "Greek Salad", "Slice some cucumbers, tomatoes, red onion. Mix with olives and feta cheese, drizzle with extra virgin olive oil, and use dried oregano for a refreshing Mediterranean delight.");
        greekSalad.addIngredient(new Item(ItemType.CHEESE));
        greekSalad.addIngredient(new Item(ItemType.TOMATO));
        greekSalad.addIngredient(new Item(ItemType.ONION));
        greekSalad.addIngredient(new Item(ItemType.CUCUMBER));
        // ... add more ingredients
        recipeRepository.save(greekSalad);

        Recipe spaghettiBolognese = new Recipe(2L, "Spaghetti Bolognese", "Brow a mix of ground beef and pork with chopped onion and garlic. Use tomato sauce, tomato paste, red wine, then add spices for flavorful sauce.");
        spaghettiBolognese.addIngredient(new Item(ItemType.ONION));
        spaghettiBolognese.addIngredient(new Item(ItemType.GARLIC));
        spaghettiBolognese.addIngredient(new Item(ItemType.WINE));
        spaghettiBolognese.addIngredient(new Item(ItemType.TOMATO));
        spaghettiBolognese.addIngredient(new Item(ItemType.GROUND_MEAT));
        // ... add more ingredients
        recipeRepository.save(spaghettiBolognese);

    }
}
