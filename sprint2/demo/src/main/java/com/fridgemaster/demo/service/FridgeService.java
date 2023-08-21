package com.fridgemaster.demo.service;

import com.fridgemaster.demo.entity.Fridge;
import com.fridgemaster.demo.entity.Item;

import java.rmi.NoSuchObjectException;
import java.util.List;
import java.util.Optional;

public interface FridgeService {
    List<Fridge> findAllFridges();
    Optional<Fridge> findFridgeById(Long fridgeId);
    Fridge saveFridge(Fridge fridge);
    Fridge updateFridge(Fridge fridge);
    void deleteFridge(Long fridgeId);

    // move to ItemService?
    List<Item> getAllItemFromFridgeById(Long fridgeId) throws NoSuchObjectException;
    Item saveItemInFridge(Long fridgeId, Item item) throws NoSuchObjectException;
    Item updateItemInFridge(Long fridgeId, Item item) throws NoSuchObjectException;
    void deleteItemFromFridge(Long fridgeId, Long itemId) throws NoSuchObjectException;
}
