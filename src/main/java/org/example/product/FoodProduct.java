package org.example.product;

import java.time.LocalDate;

public class FoodProduct extends Product implements Perishable {
   private final LocalDate expiryDate;

    public FoodProduct(String sku, String name, double netPrice, LocalDate expiryDate) {
       super(sku, name, netPrice);

       this.expiryDate = expiryDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    @Override
    public String getCategory(){
        return "JEDZENIE";
    }

    @Override
    public double getShippingCost() {
        return 29.99;
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
