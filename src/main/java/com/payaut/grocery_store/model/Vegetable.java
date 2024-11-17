package com.payaut.grocery_store.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Vegetable extends Item {
    private double weightInGrams;

    public Vegetable(double pricePerUnitPer100g, double weightInGrams) {
        super(pricePerUnitPer100g);
        this.weightInGrams = weightInGrams;
    }

    public double calculateDiscountedPrice() {
        double price = getPricePerUnit() * (weightInGrams / 100.0);
    //    double basePrice = (weightInGrams / 100) * price;
        double discount = 0.0;

        if(weightInGrams <= 0)
        {
            return 0.00;
        }

        if (weightInGrams <= 100) {
            discount = 0.05; // // 5% discount
        }else if (weightInGrams <= 500) {
            discount = 0.07; // 7% discount
        }else if (weightInGrams > 500) {
            discount = 0.10; // 10% discount
        }
        double discountedPrice = price * (1 - discount);

        // Round the discounted price to 2 decimal places
        BigDecimal roundedPrice = new BigDecimal(discountedPrice).setScale(2, RoundingMode.HALF_UP);

        return roundedPrice.doubleValue();
    }

    // Getter and setter for weightInGrams
    public double getWeightInGrams() {
        return weightInGrams;
    }

    public void setWeightInGrams(int weightInGrams) {
        this.weightInGrams = weightInGrams;
    }

    public double getPricePerUnitPer100g() {
        return getPricePerUnit() * (weightInGrams / 100.0);
    }
}
