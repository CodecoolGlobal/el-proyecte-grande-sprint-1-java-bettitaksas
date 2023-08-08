package com.fridgemaster.demo.model;

import java.time.LocalDate;
import java.util.UUID;

public final class Item {
    private UUID id;
    private ItemType type;
    //private int weight;
    //private double cal;
    private LocalDate expirationDate;

    public Item( ItemType itemType, LocalDate expirationDate) {
        this.id = UUID.randomUUID();
        this.type = itemType;
        //this.weight = weight;
        //this.cal = cal;
        this.expirationDate = expirationDate;
    }

    public UUID getId() {
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
}
