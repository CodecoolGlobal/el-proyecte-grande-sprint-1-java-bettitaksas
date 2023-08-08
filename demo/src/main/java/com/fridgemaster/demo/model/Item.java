package com.fridgemaster.demo.model;

import java.time.LocalDate;
import java.util.UUID;

public final class Item {
    private UUID id;
    private String name;
    private int weight;
    private double cal;
    private LocalDate expirationDate;

    public Item( String name, int weight, double cal, LocalDate expirationDate) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.weight = weight;
        this.cal = cal;
        this.expirationDate = expirationDate;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public double getCal() {
        return cal;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }
}
