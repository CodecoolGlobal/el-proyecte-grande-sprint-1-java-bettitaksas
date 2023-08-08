package com.fridgemaster.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Fridge {
    private UUID id;
    List<Item> fridgeContent;

    public Fridge() {
        id = UUID.randomUUID();
        fridgeContent = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public List<Item> getFridgeContent() {
        return fridgeContent;
    }

    public void addContentToFridge(Item item){
        fridgeContent.add(item);
    }
    public void removeContentFromFridge(UUID id){
        fridgeContent.removeIf(c -> c.getId().equals(id));
    }


}
