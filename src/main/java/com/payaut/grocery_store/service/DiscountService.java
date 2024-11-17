package com.payaut.grocery_store.service;

import com.payaut.grocery_store.model.*;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {

    public double getTotalCost(Order order) {
        double total = 0.0;

        for (Object item : order.getItems()) {
            if (item instanceof Bread) {
                total += ((Bread) item).calculateDiscountedPrice(); // assuming quantity 1 for simplicity
            } else if (item instanceof Vegetable) {
                total += ((Vegetable) item).calculateDiscountedPrice();
            } else if (item instanceof Beer) {
                total += ((Beer) item).calculateDiscountedPrice();
            }
        }

        return total;
    }


    public String getReceipt(Order order) {
        return order.generateReceipt();
    }

    public String getCurrentDiscountRules() {
        return "Bread discounts: 3 days - buy 1 take 2, 6 days - buy 1 take 3. \n" +
                "Vegetables: 5% off for 0-100g, 7% for 100-500g, 10% for >500g.\n" +
                "Beer: Discounts for packs of 6. Belgium: €3, Dutch: €2, German: €4.";
    }

    public String getCurrentPrices() {
        return "Bread: €1.00, Vegetable: €1.00 per 100g, Beer: €0.50 per bottle.";
    }
}

