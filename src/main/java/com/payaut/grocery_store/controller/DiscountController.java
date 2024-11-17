package com.payaut.grocery_store.controller;

import com.payaut.grocery_store.model.Order;
import com.payaut.grocery_store.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @PostMapping("/calculateTotal")
    public double calculateTotal(@RequestBody Order order) {
        return discountService.getTotalCost(order);
    }

    @PostMapping("/generateReceipt")
    public String generateReceipt(@RequestBody Order order) {
        return discountService.getReceipt(order);
    }

    @GetMapping("/discountRules")
    public String getDiscountRules() {
        return discountService.getCurrentDiscountRules();
    }

    @GetMapping("/itemPrices")
    public String getItemPrices() {
        return discountService.getCurrentPrices();
    }
}
