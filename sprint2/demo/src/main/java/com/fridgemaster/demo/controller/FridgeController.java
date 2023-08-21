package com.fridgemaster.demo.controller;

import com.fridgemaster.demo.entity.Fridge;
import com.fridgemaster.demo.entity.Item;
import com.fridgemaster.demo.service.FridgeService;
import org.springframework.web.bind.annotation.*;

import java.rmi.NoSuchObjectException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fridges")
public class FridgeController {

    private final FridgeService fridgeService;


    public FridgeController(FridgeService fridgeService) {
        this.fridgeService = fridgeService;
    }

    @GetMapping
    public List<Fridge> findAllFridges() {
        return fridgeService.findAllFridges();
    }

    @GetMapping("/{fridgeId}")
    public Optional<Fridge> findFridgeById(@PathVariable("fridgeId") Long fridgeId) {
        return fridgeService.findFridgeById(fridgeId);
    }

    @PostMapping
    public Fridge saveFridge(@RequestBody Fridge fridge) {
        return fridgeService.saveFridge(fridge);
    }

    @PutMapping
    public Fridge updateFridge(@RequestBody Fridge fridge) {
        return fridgeService.updateFridge(fridge);
    }

    @DeleteMapping("/{fridgeId}")
    public void deleteFridge(@PathVariable("fridgeId") Long fridgeId) {
        fridgeService.deleteFridge(fridgeId);
    }

    @GetMapping("/{fridgeId}/items")
    public List<Item> getAllItemFromFridgeById(@PathVariable("fridgeId") Long fridgeId) throws NoSuchObjectException {
        return fridgeService.getAllItemFromFridgeById(fridgeId);
    }

    @PostMapping("/{fridgeId}/items")
    public Item saveItemInFridge(@PathVariable Long fridgeId, @RequestBody Item item) throws NoSuchObjectException {
        Optional<Fridge> optionalFridge = fridgeService.findFridgeById(fridgeId);
        if (optionalFridge.isPresent()) {
            Fridge fridge = optionalFridge.get();
            fridge.addItem(item);
            fridgeService.saveFridge(fridge);
            return item;
        } throw new NoSuchObjectException("There is no fridge with this id.");
    }

    @PutMapping("/{fridgeId}/items")
    public Item updateItemInFridge(@PathVariable Long fridgeId, @RequestBody Item item) throws NoSuchObjectException {
        Optional<Fridge> optionalFridge = fridgeService.findFridgeById(fridgeId);
        if (optionalFridge.isPresent()) {
            optionalFridge.get().addItem(item);
            return item;
        } throw new NoSuchObjectException("There is no fridge with this id.");

    }

    //void deleteItemFromFridge(Long fridgeId, Long itemId) throws NoSuchObjectException;
    @DeleteMapping("/{fridgeId}/items/{itemId}")
    public void deleteItemFromFridge(@PathVariable Long fridgeId, @PathVariable Long itemId) throws NoSuchObjectException {
        Optional<Fridge> optionalFridge = fridgeService.findFridgeById(fridgeId);
        if (optionalFridge.isPresent()) {
            optionalFridge.get().removeItem(itemId);
        } throw new NoSuchObjectException("There is no fridge with this id.");
    }

}
