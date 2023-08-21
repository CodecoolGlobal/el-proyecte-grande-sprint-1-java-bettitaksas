package com.fridgemaster.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "fridge")
public class Fridge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fridge_id")
    private Long id;

    @OneToMany(mappedBy = "fridge", cascade = CascadeType.ALL)
    @Column(name = "items")
    @JsonIgnoreProperties("fridge")
    private List<Item> items;

    public Fridge() {
        this.items = new ArrayList<>();
    }

    public Fridge(Long id) {
        this.id = id;
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
        item.setFridge(this);
    }

    public void removeItem(Long itemId) throws NoSuchObjectException {
        items.removeIf(item -> item.getId().equals(itemId));

        Optional<Item> optionalItem = items.stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst();
        if (optionalItem.isPresent()) {
            optionalItem.get().setFridge(null);
        } throw new NoSuchObjectException("There is no item with this id.");
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
