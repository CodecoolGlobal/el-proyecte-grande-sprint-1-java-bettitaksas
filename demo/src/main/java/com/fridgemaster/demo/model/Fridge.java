package com.fridgemaster.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fridge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    List<Item> fridgeItems = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public List<Item> getFridgeItems() {
        return fridgeItems;
    }

    public void addItemToFridge(Item item){
        fridgeItems.add(item);
    }
    public void deleteItemFromFridge(Long id){
        fridgeItems.removeIf(c -> c.getId().equals(id));
    }


}


