package com.payaut.grocery_store.model;

public class Beer extends Item {
    private Origin origin; // e.g., "Belgium", "Dutch", "German"
    private int quantity;

    public Beer(double pricePerUnit, String origin, int quantity) {
        super(pricePerUnit);
        this.origin = Origin.fromString(origin);
        this.quantity = quantity;
    }

    public double calculateDiscountedPrice() {
        double totalPrice = 0.0;
        int fullPacksOf6 = quantity / 6;  // Number of full packs of 6
        int remainingBeers = quantity % 6;  // Remaining beers that don't form a full pack

        if (quantity < 0) {
            return 0.0;  // Return 0 if the quantity is invalid (negative)
        }

        // If price per unit is 0, the total price will also be 0
        if (getPricePerUnit() == 0) {
            return 0.0;  // If price per unit is 0, return 0.0
        }

        // Apply discount for full packs of 6
        if (fullPacksOf6 > 0) {
            // Calculate price for full packs of 6 beers
            totalPrice += fullPacksOf6 * 6 * getPricePerUnit();
            totalPrice = applyPackDiscount(totalPrice);
        }

        // Add price for remaining beers (without discount)
        if (remainingBeers > 0) {
            totalPrice += remainingBeers * getPricePerUnit();
        }

        return totalPrice;
    }

    /**
     * Apply the discount for packs of 6 beers based on beer type.
     * @param totalPrice The initial price of the beer pack.
     * @return The price after applying the discount.
     */
    private double applyPackDiscount(double totalPrice) {
        double discount = origin.getDiscount();
        return totalPrice - discount;  // Apply discount
    }
}
