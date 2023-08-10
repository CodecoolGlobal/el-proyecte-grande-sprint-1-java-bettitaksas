package com.fridgemaster.demo.controller;

import com.fridgemaster.demo.model.Fridge;
import com.fridgemaster.demo.model.Item;
import com.fridgemaster.demo.service.FridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/fridges")
public class FridgeController {

    private final FridgeService fridgeService;

    @Autowired
    public FridgeController(FridgeService fridgeService) {
        this.fridgeService = fridgeService;
    }

    @GetMapping("")
    public List<Fridge> getFridges(){
     return fridgeService.getFridges();
    }

    @GetMapping("/{id}")
    public List<Item> getAllItemFromFridgeById(@PathVariable UUID id){
        return fridgeService.getFridgeItems(id);
    }

    @PostMapping("/{fridgeId}")
    public void addNewItem(@PathVariable UUID fridgeId, @RequestBody Item item) {
        System.out.println(item.toString());
        fridgeService.addItem(fridgeId, item);
    }
    @PostMapping("/")
    public void startNewFridge(){
        fridgeService.startNewFridge();
    }

    //put - pl. mennyiség fele elfogy
/*    @PutMapping("/{itemId}")
    public void updateItemQuantity(@PathVariable UUID itemId, @RequestBody Item updatedItem) {
        fridgeService.updateItem(itemId, updatedItem.getQuantity(), updatedItem.get...());
    }*/

    @DeleteMapping("/{fridgeId}/{itemId}")
    public void deleteItem(@PathVariable UUID fridgeId, @PathVariable UUID itemId){
        fridgeService.deleteItem(fridgeId, itemId);
    }

}
