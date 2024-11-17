package com.payaut.grocery_store.model;

public enum Origin {
    BELGIUM(3.00),   // €3 discount for Belgium beer
    DUTCH(2.00),     // €2 discount for Dutch beer
    GERMAN(4.00),
    INVALID(0.00);// no discount

    private final double discount;

    Origin(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public static Origin fromString(String origin) {
        // Convert the origin string to the corresponding enum value
        switch (origin.toLowerCase()) {
            case "belgium":
                return BELGIUM;
            case "dutch":
                return DUTCH;
            case "german":
                return GERMAN;
            default:
                return INVALID;
        }
    }
}

