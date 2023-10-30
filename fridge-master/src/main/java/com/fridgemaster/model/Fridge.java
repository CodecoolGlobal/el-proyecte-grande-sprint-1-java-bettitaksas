package com.fridgemaster.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fridge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    List<Item> fridgeItems = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "fridge")
    @JoinColumn(name = "user_id")
    @JsonBackReference
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


