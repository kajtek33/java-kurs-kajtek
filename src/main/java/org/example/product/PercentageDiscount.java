package org.example.product;

public class PercentageDiscount implements DiscountPolicy {
    private final double percent;

    public PercentageDiscount(double percent) {
        this.percent = percent;
    }

    @Override
    public double apply(double price) {
        double discount = price * (percent / 100);
        double finalSum = price - discount;
        return finalSum;
    }

    @Override
    public String getName() {
        return "RABAT: " + percent + "%";
    }
}
