package com.fridgemaster.demo.model;


import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FridgeRepository {
    List<Fridge> fridges;

    public FridgeRepository() {
        this.fridges = new ArrayList<>();
    }

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

    public void deleteItemFromFridge(UUID fridgeID, UUID itemId){
        Optional< Fridge> fridge = fridges.stream().filter(c -> c.getId().equals(fridgeID)).findFirst();

        if(fridge.isPresent()){
            fridge.get().deleteItemFromFridge(itemId);
        }

    }

    @PostConstruct
    private void init(){
        Fridge fridge = new Fridge();

        fridges.add(fridge);
    }

    public List<Fridge> getFridges() {
        return fridges;
    }
}
