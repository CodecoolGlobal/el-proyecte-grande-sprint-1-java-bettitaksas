package com.fridgemaster.demo.model;


import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class FridgeRepository {
    List<Fridge> fridges;

    public Optional<Fridge> getFridgeContent(UUID id){
        return fridges.stream().filter(c -> c.getId().equals(id)).findFirst();
    }
}
