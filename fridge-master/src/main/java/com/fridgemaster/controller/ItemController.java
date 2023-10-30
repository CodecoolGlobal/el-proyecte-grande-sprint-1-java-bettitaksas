package com.fridgemaster.controller;

import com.fridgemaster.model.Item;
import com.fridgemaster.model.ItemType;
import com.fridgemaster.service.FridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    FridgeService fridgeService;
    @Autowired
    public ItemController(FridgeService fridgeService){
        this.fridgeService = fridgeService;
    }

    @GetMapping("")
    public List<ItemType> getAllItemTypes(){
        return Arrays.stream(ItemType.values()).toList();
    }

    @PostMapping("")
    public void addItemToFridge(@RequestBody Long fridgeId, @RequestBody Item item){
        fridgeService.addItem(fridgeId, item);
    }
}
