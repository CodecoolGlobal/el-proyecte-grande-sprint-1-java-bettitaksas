package com.fridgemaster.demo.model;
import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private List<Item> ingredients;

    public Recipe() {
        this.ingredients = new ArrayList<>();
    }

    public List<Item> getIngredients() {
        return ingredients;
    }
}
