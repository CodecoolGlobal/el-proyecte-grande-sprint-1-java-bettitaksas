package com.fridgemaster.demo.populator;


import com.fridgemaster.demo.model.*;
import com.fridgemaster.demo.repository.FridgeRepository;
import com.fridgemaster.demo.repository.ItemRepository;
import com.fridgemaster.demo.repository.RecipeRepository;
import com.fridgemaster.demo.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;

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
    private final UserRepository userRepository;
    private final EntityManager entityManager;

    @Autowired
    public DataPopulator(FridgeRepository fridgeRepository, ItemRepository itemRepository, RecipeRepository recipeRepository, UserRepository userRepository, EntityManager entityManager) {
        this.fridgeRepository = fridgeRepository;
        this.itemRepository = itemRepository;
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        if (fridgeRepository.count() == 0 && recipeRepository.count() == 0) {
            populateData();
        }
    }

    public void populateData() {
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
        Item greekSaladIngredient1 = new Item(ItemType.CHEESE);
        Item greekSaladIngredient2 = new Item(ItemType.TOMATO);
        Item greekSaladIngredient3 = new Item(ItemType.ONION);
        Item greekSaladIngredient4 = new Item(ItemType.CUCUMBER);
        greekSalad.addIngredient(greekSaladIngredient1);
        greekSalad.addIngredient(greekSaladIngredient2);
        greekSalad.addIngredient(greekSaladIngredient3);
        greekSalad.addIngredient(greekSaladIngredient4);
        itemRepository.saveAll(Arrays.asList(
                entityManager.merge(greekSaladIngredient1),
                entityManager.merge(greekSaladIngredient2),
                entityManager.merge(greekSaladIngredient3),
                entityManager.merge(greekSaladIngredient4)
        ));

        // ... add more ingredients
        recipeRepository.save(greekSalad);

        Recipe spaghettiBolognese = new Recipe("Spaghetti Bolognese", "Brow a mix of ground beef and pork with chopped onion and garlic. Use tomato sauce, tomato paste, red wine, then add spices for flavorful sauce.");
        Item spaghettiBologneseIngredient1 = new Item(ItemType.GARLIC);
        Item spaghettiBologneseIngredient2 = new Item(ItemType.WINE);
        Item spaghettiBologneseIngredient3 = new Item(ItemType.TOMATO);
        Item spaghettiBologneseIngredient4 = new Item(ItemType.GROUND_MEAT);
        spaghettiBolognese.addIngredient(spaghettiBologneseIngredient1);
        spaghettiBolognese.addIngredient(spaghettiBologneseIngredient2);
        spaghettiBolognese.addIngredient(spaghettiBologneseIngredient3);
        spaghettiBolognese.addIngredient(spaghettiBologneseIngredient4);
        itemRepository.saveAll(Arrays.asList(
                entityManager.merge(spaghettiBologneseIngredient1),
                entityManager.merge(spaghettiBologneseIngredient2),
                entityManager.merge(spaghettiBologneseIngredient3),
                entityManager.merge(spaghettiBologneseIngredient4)
        ));

        // ... add more ingredients
        recipeRepository.save(spaghettiBolognese);

        // ... add more recipes

        User user1 = new User("test1", "pass");
        userRepository.save(user1);

        fridge1.setUser(user1);

        user1.setFridge(fridge1);
        userRepository.save(user1);

    }
}
