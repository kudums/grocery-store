package com.payaut.grocery_store.service;

import com.payaut.grocery_store.model.Beer;
import com.payaut.grocery_store.model.Bread;
import com.payaut.grocery_store.model.Order;
import com.payaut.grocery_store.model.Vegetable;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DiscountServiceTest {

    @Test
    public void testCalculateTotalCost() {
        Bread bread = new Bread(1.00, 3, 5);  // 3-day-old bread
        Vegetable vegetable = new Vegetable(1.00, 200); // 200g vegetable
        Beer beer = new Beer(0.50, "Dutch", 6);  // 6 Dutch beers

        Order order = new Order(List.of(bread, vegetable, beer));
        DiscountService service = new DiscountService();

        double total = service.getTotalCost(order);
        assertEquals(6.86, total, 0.01);
    }

    @Test
    public void testGenerateReceipt() {
        Bread bread = new Bread(1.00, 3, 5);
        Vegetable vegetable = new Vegetable(1.00, 200);
        Beer beer = new Beer(0.50, "Dutch", 6);

        Order order = new Order(List.of(bread, vegetable, beer));
        DiscountService service = new DiscountService();

        String receipt = service.getReceipt(order);
        System.out.println("receipt   " + receipt);
        assertTrue(receipt.contains("Bread - €4.0"));
        assertTrue(receipt.contains("Vegetable - €1.86"));
        assertTrue(receipt.contains("Beer - €1.0"));
        assertTrue(receipt.contains("Total: €6.86"));
    }

    // Bread Test Cases
    @Test
    public void testBreadWithAgeGreaterThan6Days() {
        Bread bread = new Bread(1.00, 7, 3);  // Invalid bread (older than 6 days)
        double price = bread.calculateDiscountedPrice();
        assertEquals(0.00, price, 0.01);
    }

    @Test
    public void testBreadWithAge6Days() {
        Bread bread = new Bread(1.00, 6, 2);  // "Pay 1 get 3" discount
        double price = bread.calculateDiscountedPrice();
        assertEquals(1.00, price, 0.01);  // Should only pay for 1 bread
    }

    @Test
    public void testBreadWithAge3Days() {
        Bread bread = new Bread(1.00, 3, 2);  // "Buy 1 get 2" discount
        double price = bread.calculateDiscountedPrice();
        assertEquals(2.00, price, 0.01);  // Should pay for 2 breads
    }

    @Test
    public void testBreadWithAge1Day() {
        Bread bread = new Bread(1.00, 1, 1);  // No discount
        double price = bread.calculateDiscountedPrice();
        assertEquals(1.00, price, 0.01);  // Should pay full price
    }

    @Test
    public void testBreadWithInvalidQuantity() {
        Bread bread = new Bread(1.00, 3, 0);
        double price = bread.calculateDiscountedPrice();
        assertEquals(0.00, price, 0.01);  // Should result in no price
    }

    // Vegetable Test Cases
    @Test
    public void testVegetableWithWeightUnder100g() {
        Vegetable vegetable = new Vegetable(1.00, 50);  // 50g vegetable (5% discount)
        double price = vegetable.calculateDiscountedPrice();
        assertEquals(0.47, price, 0.01);  // 5% discount applied
    }

    @Test
    public void testVegetableWithWeightBetween100gAnd500g() {
        Vegetable vegetable = new Vegetable(1.00, 200);  // 200g vegetable (7% discount)
        double price = vegetable.calculateDiscountedPrice();
        assertEquals(1.86, price, 0.01);  // 7% discount applied
    }

    @Test
    public void testVegetableWithWeightOver500g() {
        Vegetable vegetable = new Vegetable(1.00, 600);  // 600g vegetable (10% discount)
        double price = vegetable.calculateDiscountedPrice();
        assertEquals(5.40, price, 0.01);  // 10% discount applied
    }

    @Test
    public void testVegetableWithNegativeWeight() {
        Vegetable vegetable = new Vegetable(1.00, -100);  // Invalid negative weight
        double price = vegetable.calculateDiscountedPrice();
        assertEquals(0.00, price, 0.01);
    }

    @Test
    public void testVegetableWithZeroWeight() {
        Vegetable vegetable = new Vegetable(1.00, 0);  // Zero weight
        double price = vegetable.calculateDiscountedPrice();
        assertEquals(0.00, price, 0.01);  // Should result in no price
    }

    // Beer Test Cases
    @Test
    public void testBeerWithInvalidOrigin() {
        Beer beer = new Beer(1.00, "Invalid", 6);  // Invalid origin
        double price = beer.calculateDiscountedPrice();
        assertEquals(6.00, price, 0.01);
    }

    @Test
    public void testBeerWithQuantityNotEqualTo6() {
        Beer beer = new Beer(2.00, "Belgium", 5);  // No discount for less than 6 beers
        double price = beer.calculateDiscountedPrice();
        assertEquals(10.00, price, 0.01);  // No discount applied
    }

    @Test
    public void testBeerWithValidPackSize() {
        Beer beer = new Beer(2.00, "Belgium", 6);  // Valid 6-pack of Belgium beer
        double price = beer.calculateDiscountedPrice();
        assertEquals(9.00, price, 0.01);  // €3 discount applied for Belgium beer pack
    }

    @Test
    public void testBeerWithQuantityGreaterThan6() {
        Beer beer = new Beer(2.00, "Dutch", 12);  // 2 packs of Dutch beer
        double price = beer.calculateDiscountedPrice();
        assertEquals(22.00, price, 0.01);  // Discount applied to both packs (€2 each)
    }

    @Test
    public void testBeerWithNegativeQuantity() {
        Beer beer = new Beer(2.00, "German", -6);  // Invalid negative quantity
        double price = beer.calculateDiscountedPrice();
        assertEquals(0.00, price, 0.01);
    }

    @Test
    public void testBeerWithZeroPrice() {
        Beer beer = new Beer(0.00, "Dutch", 6);  // Zero price per unit
        double price = beer.calculateDiscountedPrice();
        assertEquals(0.00, price, 0.01);  // Price should be 0
    }

    @Test
    public void testBeerWithZeroQuantity() {
        Beer beer = new Beer(2.00, "German", 0);  // Zero quantity
        double price = beer.calculateDiscountedPrice();
        assertEquals(0.00, price, 0.01);  // No beer, no price
    }
}

