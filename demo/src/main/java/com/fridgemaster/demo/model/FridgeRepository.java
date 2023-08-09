package com.fridgemaster.demo.model;


import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FridgeRepository {
    List<Fridge> fridges;

    public List<Item> getFridgeItem(UUID id){
      Optional< Fridge> fridge = fridges.stream().filter(c -> c.getId().equals(id)).findFirst();

      if(fridge.isPresent()){

      return fridge.get().getFridgeItems();
      }
      throw new NoSuchElementException("No fridge with this ID");
    }

    public void addItemToFridge(UUID fridgeID,Item item){
        Optional< Fridge> fridge = fridges.stream().filter(c -> c.getId().equals(fridgeID)).findFirst();

        if(fridge.isPresent()){
            fridge.get().addItemToFridge(item);
        }
    }

    public void deleteItemFromFridge(UUID fridgeID, Item item){
        Optional< Fridge> fridge = fridges.stream().filter(c -> c.getId().equals(fridgeID)).findFirst();

        if(fridge.isPresent()){
            fridge.get().deleteItemFromFridge(item);
        }

    }
}
