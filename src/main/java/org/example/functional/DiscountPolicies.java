package org.example.functional;

import java.math.BigDecimal;

public class DiscountPolicies {
    static BigDecimal total(Order order) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Product product : order.items()) {
            sum = sum.add(product.price());
        }
        return sum;
    }

    public static final DiscountPolicy PREMIUM_10 = order -> {
        if (order.customer().premium()) {
            return total(order).multiply(new BigDecimal("0.10"));
        }
        return BigDecimal.ZERO;
    };

    public static final DiscountPolicy OVER_500_5 = order -> {
        if (total(order).compareTo(new BigDecimal("500")) > 0) {
            return total(order).multiply(new BigDecimal("0.05"));
        }
        return BigDecimal.ZERO;
    };

    public static final DiscountPolicy MORE_THAN_5_ITEMS_20 = order -> {
        if (order.items().size() > 5) {
            return (new BigDecimal("20"));
        }
        return BigDecimal.ZERO;
    };

    public static final DiscountPolicy ALL = PREMIUM_10.andThen(OVER_500_5).andThen(MORE_THAN_5_ITEMS_20);
}
