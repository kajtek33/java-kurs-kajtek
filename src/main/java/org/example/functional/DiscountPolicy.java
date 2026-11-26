package org.example.functional;

import java.math.BigDecimal;

@FunctionalInterface
public interface DiscountPolicy {
    BigDecimal apply(Order order);

    default DiscountPolicy andThen(DiscountPolicy next) {
        return order -> this.apply(order).add(next.apply(order));
    }

    static DiscountPolicy none() {
        return order -> BigDecimal.ZERO;
    }
}
