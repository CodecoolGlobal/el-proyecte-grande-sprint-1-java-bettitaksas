package com.fridgemaster.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
@Entity
public final class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ItemType type;
    //private int weight;
    //private double cal;
    private LocalDate expirationDate;

    public Item( ItemType itemType, LocalDate expirationDate) {
        this.type = itemType;
        //this.weight = weight;
        //this.cal = cal;
        this.expirationDate = expirationDate;
    }

    public Item(ItemType itemType) {
        this.type = itemType;
    }

    public Item() {
    }

    public Long getId() {
        return id;
    }

    public ItemType getType() {
        return type;
    }

/*    public int getWeight() {
        return weight;
    }

    public double getCal() {
        return cal;
    }*/

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", type=" + type +
                ", expirationDate=" + expirationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return type == item.type;
    }
}
