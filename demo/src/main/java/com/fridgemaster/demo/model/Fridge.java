package com.fridgemaster.demo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
public class Fridge {
    private UUID id;
    List<Item> fridgeItems;

    public Fridge() {
        id = UUID.randomUUID();
        fridgeItems = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public List<Item> getFridgeItems() {
        return fridgeItems;
    }

    public void addItemToFridge(Item item){
        fridgeItems.add(item);
    }
    public void deleteItemFromFridge(UUID id){
        fridgeItems.removeIf(c -> c.getId().equals(id));
    }


}


