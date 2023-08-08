package com.fridgemaster.demo.model;
import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private List<Item> ingredients;

    public Recipe(List<Item> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Item> getIngredients() {
        return ingredients;
    }
}
