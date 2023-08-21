package com.fridgemaster.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Enumerated
    @Column(name = "type")
    private ItemType itemType;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @ManyToOne
    @JoinColumn(name = "fridge_id")
    @JsonIgnoreProperties("items")
    private Fridge fridge;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    @JsonIgnoreProperties("ingredients")
    private Recipe recipe;

    public Item() {
    }

    public Item(ItemType itemType) {
        this.itemType = itemType;
    }

    public Item(Long id, ItemType itemType, LocalDate expirationDate) {
        this.id = id;
        this.itemType = itemType;
        this.expirationDate = expirationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Fridge getFridge() {
        return fridge;
    }

    public void setFridge(Fridge fridge) {
        this.fridge = fridge;
    }

}
