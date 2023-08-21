package com.fridgemaster.demo.model;
import java.util.ArrayList;
import java.util.List;

public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String name;
    @OneToMany
    private List<Item> ingredients;
    private String description;

    public Recipe(String name) {
        this.name = name;
        this.ingredients = new ArrayList<>();
        this.description = "";
    }

    public void addIngredient(Item ingredient){
        ingredients.add(ingredient);
    }

    public List<Item> getIngredients() {
        return ingredients;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                '}';
    }
}
