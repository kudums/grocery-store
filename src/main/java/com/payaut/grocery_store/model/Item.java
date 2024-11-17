package com.payaut.grocery_store.model;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,  // Use the NAME of the class as the type identifier
        include = JsonTypeInfo.As.PROPERTY,  // Include a 'type' property in the JSON
        property = "type"  // Use the 'type' property in the JSON to indicate the type
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Bread.class, name = "Bread"),
        @JsonSubTypes.Type(value = Vegetable.class, name = "Vegetable"),
        @JsonSubTypes.Type(value = Beer.class, name = "Beer")
})
public abstract class Item {
    private final double pricePerUnit;

    public Item(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public abstract double calculateDiscountedPrice();
}