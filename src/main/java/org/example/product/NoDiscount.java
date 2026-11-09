package org.example.product;

public class NoDiscount implements DiscountPolicy {

    @Override
    public Money apply(Money price) {
        return price;
    }

    @Override
    public String getName() {
        return "BRAK RABATU";
    }
}
