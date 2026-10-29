package org.example.product;

public class NoDiscount implements DiscountPolicy {

    @Override
    public double apply(double price) {
        return price;
    }

    @Override
    public String getName() {
        return "BRAK RABATU";
    }
}
