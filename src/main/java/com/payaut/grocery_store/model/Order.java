package com.payaut.grocery_store.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Order {
    private List<Item> items;

    // Default constructor for Jackson
    public Order() {
    }

    @JsonCreator
    public Order(@JsonProperty("items") List<Item> items) {
        this.items = items;
    }

    public double calculateTotalCost(List<Item> items) {
        double total = 0.0;

        // Iterate over the items and calculate the total cost
        for (Item item : items) {
            if (item instanceof Bread) {
                // Cast to Bread and calculate the discounted price for quantity 1 (or adjust logic as needed)
                total += item.calculateDiscountedPrice();
            } else if (item instanceof Vegetable) {
                // Cast to Vegetable and calculate the discounted price
                total += item.calculateDiscountedPrice();
            } else if (item instanceof Beer) {
                // Cast to Beer and calculate the discounted price
                total += item.calculateDiscountedPrice();
            }
        }

        return total;
    }


    public String generateReceipt() {
        StringBuilder receipt = new StringBuilder("Receipt:\n");

        for (Object item : items) {
            if (item instanceof Bread bread) {
                receipt.append("Bread - €").append(bread.calculateDiscountedPrice()).append("\n");
            } else if (item instanceof Vegetable vegetable) {
                receipt.append("Vegetable - €").append(vegetable.calculateDiscountedPrice()).append("\n");
            } else if (item instanceof Beer beer) {
                receipt.append("Beer - €").append(beer.calculateDiscountedPrice()).append("\n");
            }
        }

        double total = calculateTotalCost(items);
        receipt.append("Total: €").append(total);
        return receipt.toString();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}

