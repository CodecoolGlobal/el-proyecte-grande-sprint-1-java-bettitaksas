package com.fridgemaster.demo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
public class Fridge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    List<Item> fridgeItems;

    public Fridge() {
        fridgeItems = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public List<Item> getFridgeItems() {
        return fridgeItems;
    }

    public void addItemToFridge(Item item){
        fridgeItems.add(item);
    }
    public void deleteItemFromFridge(Long id){
        fridgeItems.removeIf(c -> c.getId().equals(id));
    }


}


