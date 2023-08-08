package com.fridgemaster.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/fridge")
public class FridgeController {

    @GetMapping("/{id}")
    public List<Item> getAllItemFromFridgeById(@PathVariable UUID id){
        return fridgeService.getFridgeContents(id);
    }

    @PostMapping
    public void addNewItem(@RequestBody Item item) {
        fridgeService.addItem(item);
    }

    //put - pl. mennyiség fele elfogy
    @PutMapping("/{itemId}")
    public void updateItemQuantity(@PathVariable UUID itemId, @RequestBody Item updatedItem) {
        fridgeService.updateItem(itemId, updatedItem.getQuantity(), updatedItem.get...());
    }

    @DeleteMapping("/{itemId}")
    public void deleteItem(@PathVariable UUID fridgeId, @PathVariable UUID itemId){
        fridgeService.deleteItem(fridgeId, itemId);
    }

}
