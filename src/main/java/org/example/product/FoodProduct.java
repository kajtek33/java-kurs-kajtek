package org.example.product;

import java.time.LocalDate;

public class FoodProduct extends Product implements Perishable {
   private final LocalDate expiryDate;

    public FoodProduct(String sku, String name, Money netPrice, LocalDate expiryDate) {
       super(sku, name, netPrice, Category.FOOD);

       this.expiryDate = expiryDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    @Override
    public Money getShippingCost() {
        return Money.of("29.99", getNetPrice().currency());
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (TERMIN: " + getExpiryDate() + ")";
    }

    @Override
    public boolean isExpired() {
        return expiryDate.isBefore(LocalDate.now());
    }
}
