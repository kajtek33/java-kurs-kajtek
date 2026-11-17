package org.example.product;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PercentageDiscount implements DiscountPolicy {
    private final double percent;

    @Override
    public Money apply(Money price) {
        Money discount = price.applyPercent(percent);
        Money newPrice =  price.subtract(discount);
        return newPrice;
    }

    @Override
    public String getName() {
        return "RABAT: " + percent + "%";
    }
}
