package org.example.product;

public class FixedAmountDiscount implements DiscountPolicy {

    private final Money discount;

    public FixedAmountDiscount(Money discount) {
        this.discount = discount;
    }

    @Override
    public Money apply(Money price) {
        Money minimum = Money.of("0.01", price.currency());
        if (discount.isGreaterThan(price)) {
            return minimum;
        }
        Money newPrice =  price.subtract(discount);

        if (minimum.isGreaterThan(newPrice)) {
            return minimum;
        }
        return newPrice;
    }

    @Override
    public String getName() {
        return "RABAT KWOTOWY: " + discount;
    }
}
