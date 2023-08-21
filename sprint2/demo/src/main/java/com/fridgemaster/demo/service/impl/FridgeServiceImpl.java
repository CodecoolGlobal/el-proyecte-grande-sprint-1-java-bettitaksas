package com.fridgemaster.demo.service.impl;

import com.fridgemaster.demo.entity.Fridge;
import com.fridgemaster.demo.entity.Item;
import com.fridgemaster.demo.repository.FridgeRepository;
import com.fridgemaster.demo.service.FridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.NoSuchObjectException;
import java.util.List;
import java.util.Optional;

@Service
public class FridgeServiceImpl implements FridgeService {

    private final FridgeRepository fridgeRepository;

    @Autowired
    public FridgeServiceImpl(FridgeRepository fridgeRepository) {
        this.fridgeRepository = fridgeRepository;
    }

    @Override
    public List<Fridge> findAllFridges() {
        return fridgeRepository.findAll();
    }

    @Override
    public Optional<Fridge> findFridgeById(Long fridgeId) {
        return fridgeRepository.findById(fridgeId);
    }

    @Override
    public Fridge saveFridge(Fridge fridge) {
        return fridgeRepository.save(fridge);
    }

    @Override
    public Fridge updateFridge(Fridge fridge) {
        return fridgeRepository.save(fridge);
    }

    @Override
    public void deleteFridge(Long fridgeId) {
        fridgeRepository.deleteById(fridgeId);
    }

    @Override
    public List<Item> getAllItemFromFridgeById(Long fridgeId) throws NoSuchObjectException {
        Optional<Fridge> optionalFridge = fridgeRepository.findById(fridgeId);
        if (optionalFridge.isPresent()){
            return optionalFridge.get().getItems();
        } throw new NoSuchObjectException("There is no fridge with this id.");
    }

    @Override
    public Item saveItemInFridge(Long fridgeId, Item item) throws NoSuchObjectException {
        Optional<Fridge> optionalFridge = fridgeRepository.findById(fridgeId);
        if (optionalFridge.isPresent()){
            optionalFridge.get().addItem(item);
            return item;
        } throw new NoSuchObjectException("There is no fridge with this id.");
    }

    //?? is it overwrites the existing item or creates a new one
    @Override
    public Item updateItemInFridge(Long fridgeId, Item item) throws NoSuchObjectException {
        Optional<Fridge> optionalFridge = fridgeRepository.findById(fridgeId);
        if (optionalFridge.isPresent()){
            optionalFridge.get().addItem(item);
            return item;
        } throw new NoSuchObjectException("There is no fridge with this id.");
    }

    @Override
    public void deleteItemFromFridge(Long fridgeId, Long itemId) throws NoSuchObjectException {
        Optional<Fridge> optionalFridge = fridgeRepository.findById(fridgeId);
        if (optionalFridge.isPresent()){
            optionalFridge.get().removeItem(itemId);
        } throw new NoSuchObjectException("There is no fridge with this id.");
    }
}
