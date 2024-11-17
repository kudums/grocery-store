package com.payaut.grocery_store.model;


public class Bread extends Item {
    private int ageInDays;
    private int quantity;

    public Bread(double pricePerUnit, int ageInDays, int quantity) {
        super(pricePerUnit);
        this.ageInDays = ageInDays;
        this.quantity = quantity;

    }

    @Override
    public double calculateDiscountedPrice() {
        if (quantity < 0) {
            return 0.0;  // Return 0 if the quantity is invalid (negative)
        }

        if (getPricePerUnit() <= 0) {
            return 0.0;  // Return 0 if the quantity is invalid (negative)
        }

        if (ageInDays > 6) {
            // Bread older than 6 days cannot be added to orders
            return 0.0;
        } else if (ageInDays == 6) {
            // For 6-day-old bread, pay for 1 get 3 (buy 1 get 3 discount)
            // For every 4 loaves, you pay for 1
            int discountedUnits = quantity / 4; // Group the loaves into sets of 4 (1 paid + 3 free)
            int remainingUnits = quantity % 4; // Remaining loaves not in the discount group
            return getPricePerUnit() *  (discountedUnits + (remainingUnits > 0 ? 1 : 0));
        } else if (ageInDays == 3) {
            // For 3-day-old bread, buy 1 get 2 (buy 1 get 2 discount)
            // For every 3 loaves, you pay for 2
            int discountedUnits = quantity / 3; // Group the loaves into sets of 3 (2 paid + 1 free)
            int remainingUnits = quantity % 3; // Remaining loaves not in the discount group
            return getPricePerUnit() * (discountedUnits * 2 + remainingUnits);
        } else if (ageInDays <= 1) {
            // No discount for 1-day-old or newer bread, just pay for all loaves
            return getPricePerUnit() * quantity;
        }

        // Return price if no discount applies
        return getPricePerUnit() * quantity;
    }

    public int getAgeInDays() {
        return ageInDays;
    }

    public void setAgeInDays(int ageInDays) {
        this.ageInDays = ageInDays;
    }
    // age of the bread in days

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
