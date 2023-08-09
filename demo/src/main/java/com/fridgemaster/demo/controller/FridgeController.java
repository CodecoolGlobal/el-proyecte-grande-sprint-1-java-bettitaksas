package com.fridgemaster.demo.controller;

import com.fridgemaster.demo.model.Item;
import com.fridgemaster.demo.service.FridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/fridge")
public class FridgeController {

    private final FridgeService fridgeService;

    @Autowired
    public FridgeController(FridgeService fridgeService) {
        this.fridgeService = fridgeService;
    }

    @GetMapping("/{id}")
    public List<Item> getAllItemFromFridgeById(@PathVariable UUID id){
        return fridgeService.getFridgeContents(id);
    }

    @PostMapping("/{fridgeId}")
    public void addNewItem(@PathVariable UUID fridgeId, @RequestBody Item item) {
        System.out.println(item.toString());
        fridgeService.addItem(fridgeId, item);
    }

    //put - pl. mennyiség fele elfogy
/*    @PutMapping("/{itemId}")
    public void updateItemQuantity(@PathVariable UUID itemId, @RequestBody Item updatedItem) {
        fridgeService.updateItem(itemId, updatedItem.getQuantity(), updatedItem.get...());
    }*/

    @DeleteMapping("/{item}")
    public void deleteItem(@PathVariable UUID fridgeId, @PathVariable Item item){
        fridgeService.deleteItem(fridgeId, item);
    }

}
