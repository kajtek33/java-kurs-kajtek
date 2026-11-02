package org.example.product;

public class ElectronicsOnlyDiscount implements DiscountPolicy {

    private static final double PERCENT = 10;

    @Override
    public double apply(double price) {
        double discount = price * (PERCENT / 100);
        double finalSum = price - discount;
        return finalSum;
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
