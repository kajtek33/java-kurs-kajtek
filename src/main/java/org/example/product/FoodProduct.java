package org.example.product;

import lombok.Getter;
import java.time.LocalDate;

@Getter
public class FoodProduct extends Product implements Perishable {
   private final LocalDate expiryDate;

    public FoodProduct(String sku, String name, Money netPrice, LocalDate expiryDate, Supplier supplier) {
        super(sku, name, netPrice, Category.FOOD, supplier);
        this.expiryDate = expiryDate;
    }

    public FoodProduct(String sku, String name, Money netPrice, LocalDate expiryDate) {
        this(sku, name, netPrice, expiryDate, null);
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

    @Override
    public final String getLabel() {
        return getName() + " [" + getCategory().getDisplayName() + "] — " + getNetPrice() + " (Z WYSYŁKĄ " + getTotalPrice() + ")";
    }
}
