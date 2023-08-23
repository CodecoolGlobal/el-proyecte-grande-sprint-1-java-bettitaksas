package com.fridgemaster.demo.repository;

import com.fridgemaster.demo.model.Fridge;
import com.fridgemaster.demo.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByFridge(Fridge fridge);
}
