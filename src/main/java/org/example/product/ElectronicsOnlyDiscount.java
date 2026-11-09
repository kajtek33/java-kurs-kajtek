package org.example.product;

public class ElectronicsOnlyDiscount implements DiscountPolicy {

    private static final double PERCENT = 10;

    @Override
    public Money apply(Money price) {
        Money discount = price.applyPercent(PERCENT);
        Money newPrice =  price.subtract(discount);
        return newPrice;
    }

    @Override
    public String getName() {
        return "RABAT 10% (ELEKTRONIKA)";
    }

    @Override
    public boolean appliesTo(Product p) {
        return p instanceof Electronics;
    }
}
