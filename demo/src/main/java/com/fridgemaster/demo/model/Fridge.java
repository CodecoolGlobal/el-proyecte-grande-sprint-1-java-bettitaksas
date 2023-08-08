package com.fridgemaster.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    public void deleteItemFromFridge(Item item){
        UUID itemId = item.getId();
        fridgeItems.removeIf(c -> c.getId().equals(itemId));
    }


}
