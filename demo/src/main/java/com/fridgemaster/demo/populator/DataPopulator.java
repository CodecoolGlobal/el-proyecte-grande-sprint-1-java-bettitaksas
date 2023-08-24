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

        Recipe cheesyPotatoCasserole = new Recipe("Cheesy Potato Casserole", "Slice potatoes and onions. Layer them in a dish, adding cheese between layers. Drizzle with butter. Bake until potatoes are tender and cheese is bubbly.");
        Item cheesyPotatoCasseroleIngredient1 = new Item(ItemType.CHEESE);
        Item cheesyPotatoCasseroleIngredient2 = new Item(ItemType.POTATO);
        Item cheesyPotatoCasseroleIngredient3 = new Item(ItemType.BUTTER);
        Item cheesyPotatoCasseroleIngredient4 = new Item(ItemType.ONION);
        cheesyPotatoCasserole.addIngredient(cheesyPotatoCasseroleIngredient1);
        cheesyPotatoCasserole.addIngredient(cheesyPotatoCasseroleIngredient2);
        cheesyPotatoCasserole.addIngredient(cheesyPotatoCasseroleIngredient3);
        cheesyPotatoCasserole.addIngredient(cheesyPotatoCasseroleIngredient4);
        itemRepository.saveAll(Arrays.asList(
                entityManager.merge(cheesyPotatoCasseroleIngredient1),
                entityManager.merge(cheesyPotatoCasseroleIngredient2),
                entityManager.merge(cheesyPotatoCasseroleIngredient3),
                entityManager.merge(cheesyPotatoCasseroleIngredient4)
        ));

        // ... add more ingredients
        recipeRepository.save(cheesyPotatoCasserole);

        Recipe tomatoAndCheeseOmelette = new Recipe("Tomato and Cheese Omelette", "Whisk eggs and pour into a heated pan with melted butter. Add diced tomatoes and cheese. Cook until set, then fold in half.");
        Item tomatoAndCheeseOmeletteIngredient1 = new Item(ItemType.TOMATO);
        Item tomatoAndCheeseOmeletteIngredient2 = new Item(ItemType.CHEESE);
        Item tomatoAndCheeseOmeletteIngredient3 = new Item(ItemType.EGG);
        Item tomatoAndCheeseOmeletteIngredient4 = new Item(ItemType.BUTTER);
        tomatoAndCheeseOmelette.addIngredient(tomatoAndCheeseOmeletteIngredient1);
        tomatoAndCheeseOmelette.addIngredient(tomatoAndCheeseOmeletteIngredient2);
        tomatoAndCheeseOmelette.addIngredient(tomatoAndCheeseOmeletteIngredient3);
        tomatoAndCheeseOmelette.addIngredient(tomatoAndCheeseOmeletteIngredient4);
        itemRepository.saveAll(Arrays.asList(
                entityManager.merge(tomatoAndCheeseOmeletteIngredient1),
                entityManager.merge(tomatoAndCheeseOmeletteIngredient2),
                entityManager.merge(tomatoAndCheeseOmeletteIngredient3),
                entityManager.merge(tomatoAndCheeseOmeletteIngredient4)
        ));

        // ... add more ingredients
        recipeRepository.save(tomatoAndCheeseOmelette);

        Recipe hamAndCheeseQuesadillas = new Recipe("Ham and Cheese Quesadillas", "Layer ham and cheese between tortillas. Cook in a pan with melted butter until cheese melts and tortillas are golden.");
        Item hamAndCheeseQuesadillasIngredient1 = new Item(ItemType.HAM);
        Item hamAndCheeseQuesadillasIngredient2 = new Item(ItemType.CHEESE);
        Item hamAndCheeseQuesadillasIngredient3 = new Item(ItemType.BUTTER);
        hamAndCheeseQuesadillas.addIngredient(hamAndCheeseQuesadillasIngredient1);
        hamAndCheeseQuesadillas.addIngredient(hamAndCheeseQuesadillasIngredient2);
        hamAndCheeseQuesadillas.addIngredient(hamAndCheeseQuesadillasIngredient3);
        itemRepository.saveAll(Arrays.asList(
                entityManager.merge(hamAndCheeseQuesadillasIngredient1),
                entityManager.merge(hamAndCheeseQuesadillasIngredient2),
                entityManager.merge(hamAndCheeseQuesadillasIngredient3)
        ));

        // ... add more ingredients
        recipeRepository.save(hamAndCheeseQuesadillas);

        Recipe tofuAndVegetableStirFry = new Recipe("Tofu and Vegetable Stir-Fry", "Cube tofu and stir-fry with sliced carrots, broccoli, and onions. Season with your choice of sauce (soy, teriyaki, etc.).");
        Item tofuAndVegetableStirFryIngredient1 = new Item(ItemType.TOFU);
        Item tofuAndVegetableStirFryIngredient2 = new Item(ItemType.CARROT);
        Item tofuAndVegetableStirFryIngredient3 = new Item(ItemType.BROCCOLI);
        Item tofuAndVegetableStirFryIngredient4 = new Item(ItemType.ONION);
        tofuAndVegetableStirFry.addIngredient(tofuAndVegetableStirFryIngredient1);
        tofuAndVegetableStirFry.addIngredient(tofuAndVegetableStirFryIngredient2);
        tofuAndVegetableStirFry.addIngredient(tofuAndVegetableStirFryIngredient3);
        tofuAndVegetableStirFry.addIngredient(tofuAndVegetableStirFryIngredient4);
        itemRepository.saveAll(Arrays.asList(
                entityManager.merge(tofuAndVegetableStirFryIngredient1),
                entityManager.merge(tofuAndVegetableStirFryIngredient2),
                entityManager.merge(tofuAndVegetableStirFryIngredient3),
                entityManager.merge(tofuAndVegetableStirFryIngredient4)
        ));

        // ... add more ingredients
        recipeRepository.save(tofuAndVegetableStirFry);

        Recipe beerMarinatedGroundMeat = new Recipe("Beer-Marinated Ground Meat", "Mix ground meat with minced garlic and spices. Add beer and marinate for a few hours. Cook in a pan until browned.");
        Item BeerMarinatedGroundMeatIngredient1 = new Item(ItemType.GROUND_MEAT);
        Item BeerMarinatedGroundMeatIngredient2 = new Item(ItemType.BEER);
        Item BeerMarinatedGroundMeatIngredient3 = new Item(ItemType.GARLIC);
        beerMarinatedGroundMeat.addIngredient(BeerMarinatedGroundMeatIngredient1);
        beerMarinatedGroundMeat.addIngredient(BeerMarinatedGroundMeatIngredient2);
        beerMarinatedGroundMeat.addIngredient(BeerMarinatedGroundMeatIngredient3);
        itemRepository.saveAll(Arrays.asList(
                entityManager.merge(tofuAndVegetableStirFryIngredient1),
                entityManager.merge(tofuAndVegetableStirFryIngredient2),
                entityManager.merge(tofuAndVegetableStirFryIngredient3)
        ));

        // ... add more ingredients
        recipeRepository.save(beerMarinatedGroundMeat);

        Recipe chickenAndCheeseStuffedTomatoes = new Recipe("Chicken and Cheese Stuffed Tomatoes", "Sauté chicken and minced garlic. Mix with cheese and stuff into hollowed-out tomatoes. Bake until tomatoes are soft and cheese is melted.");
        Item chickenAndCheeseStuffedTomatoesIngredient1 = new Item(ItemType.CHICKEN);
        Item chickenAndCheeseStuffedTomatoesIngredient2 = new Item(ItemType.CHEESE);
        Item chickenAndCheeseStuffedTomatoesIngredient3 = new Item(ItemType.TOMATO);
        Item chickenAndCheeseStuffedTomatoesIngredient4 = new Item(ItemType.GARLIC);
        chickenAndCheeseStuffedTomatoes.addIngredient(chickenAndCheeseStuffedTomatoesIngredient1);
        chickenAndCheeseStuffedTomatoes.addIngredient(chickenAndCheeseStuffedTomatoesIngredient2);
        chickenAndCheeseStuffedTomatoes.addIngredient(chickenAndCheeseStuffedTomatoesIngredient3);
        chickenAndCheeseStuffedTomatoes.addIngredient(chickenAndCheeseStuffedTomatoesIngredient4);
        itemRepository.saveAll(Arrays.asList(
                entityManager.merge(chickenAndCheeseStuffedTomatoesIngredient1),
                entityManager.merge(chickenAndCheeseStuffedTomatoesIngredient2),
                entityManager.merge(chickenAndCheeseStuffedTomatoesIngredient3),
                entityManager.merge(chickenAndCheeseStuffedTomatoesIngredient4)
        ));

        // ... add more ingredients
        recipeRepository.save(chickenAndCheeseStuffedTomatoes);



        // ... add more recipes

        User user1 = new User("test1", "pass");
        userRepository.save(user1);

        fridge1.setUser(user1);

        user1.setFridge(fridge1);
        userRepository.save(user1);

    }
}
