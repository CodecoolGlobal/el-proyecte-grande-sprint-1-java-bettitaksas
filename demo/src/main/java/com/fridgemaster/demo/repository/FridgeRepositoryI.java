package com.fridgemaster.demo.repository;


import com.fridgemaster.demo.model.Fridge;
import com.fridgemaster.demo.model.Item;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.*;


public class FridgeRepositoryI {
    List<Fridge> fridges;

    public FridgeRepositoryI() {
        this.fridges = new ArrayList<>();
    }

    public void startNewFridge(){
        Fridge fridge = new Fridge();
        fridges.add(fridge);
        System.out.println(fridge.getId());
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

    public void deleteItemFromFridge(Long fridgeID, Long itemId){
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
