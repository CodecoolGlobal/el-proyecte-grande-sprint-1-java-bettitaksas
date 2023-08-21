package com.fridgemaster.demo.service.impl;

import com.fridgemaster.demo.entity.Item;
import com.fridgemaster.demo.repository.ItemRepository;
import com.fridgemaster.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }
}
