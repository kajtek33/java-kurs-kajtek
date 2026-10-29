package org.example.product;

public class FixedAmountDiscount implements DiscountPolicy {

    private final double discount;

    public FixedAmountDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public double apply(double price) {
        double afterDiscount = price - discount;
        return Math.max(afterDiscount, 0.01);
    }

    @Override
    public String getName() {
        return "RABAT KWOTOWY: " + discount + " ZŁ";
    }
}
