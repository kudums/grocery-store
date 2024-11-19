package com.payaut.grocery_store.controller;

import com.payaut.grocery_store.model.Order;
import com.payaut.grocery_store.service.DiscountService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:8080", "http://grocery-store-production-d971.up.railway.app", "https://grocery-store-production-d971.up.railway.app"})
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @PostMapping("/calculateTotal")
    public double calculateTotal(@RequestBody @Schema(example = "{\n" +
            "\"items\": [\n" +
            "  { \"type\": \"Bread\", \"pricePerUnit\": 1.00, \"ageInDays\": 3, \"quantity\": 3 },\n" +
            "  { \"type\": \"Vegetable\", \"pricePerUnitPer100g\": 1.00, \"weightInGrams\": 200 },\n" +
            "  { \"type\": \"Beer\", \"pricePerUnit\": 0.50, \"origin\": \"Dutch\", \"quantity\": 6 }\n" +
            "]\n" +
            "}") Order order) {
        return discountService.getTotalCost(order);
    }

    @PostMapping("/generateReceipt")
    public String generateReceipt(@RequestBody @Schema(example = "{\n" +
            "\"items\": [\n" +
            "  { \"type\": \"Bread\", \"pricePerUnit\": 1.00, \"ageInDays\": 3, \"quantity\": 3 },\n" +
            "  { \"type\": \"Vegetable\", \"pricePerUnitPer100g\": 1.00, \"weightInGrams\": 200 },\n" +
            "  { \"type\": \"Beer\", \"pricePerUnit\": 0.50, \"origin\": \"Dutch\", \"quantity\": 6 }\n" +
            "]\n" +
            "}") Order order) {
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
