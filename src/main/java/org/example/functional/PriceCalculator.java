package org.example.functional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.function.Function;

public class PriceCalculator {
    public static final Function<Product, BigDecimal> BASE_PRICE = Product::price;
    public static final Function<BigDecimal, BigDecimal> WITH_VAT = price -> price.multiply(new BigDecimal("1.23"));
    public static final Function<BigDecimal, BigDecimal> WITH_SHIPPING = price -> price.add(new BigDecimal("15"));
    public static final Function<BigDecimal, String> FORMAT = f -> String.format(new Locale("pl", "PL"), "%,.2f ZŁ", f);
    public static final Function<Product, String> FULL_PRICE_LABEL = BASE_PRICE.andThen(WITH_VAT).andThen(WITH_SHIPPING).andThen(FORMAT);
    public static final Function<Product, String> SHIPPING_THEN_VAT_LABEL = BASE_PRICE.andThen(WITH_SHIPPING).andThen(WITH_VAT).andThen(FORMAT);
    public static final Function<Product, String> SHIPPING_VAT_DISCOUNT_LABEL = BASE_PRICE.andThen(WITH_SHIPPING).andThen(WITH_VAT).andThen(discount(new BigDecimal("10"))).andThen(FORMAT);
    public static final Function<Product, String> DISCOUNT_SHIPPING_VAT_LABEL = BASE_PRICE.andThen(discount(new BigDecimal("10"))).andThen(WITH_SHIPPING).andThen(WITH_VAT).andThen(FORMAT);

    public static Function<BigDecimal, BigDecimal> discount(BigDecimal percent) {
        BigDecimal fraction = percent.divide(new BigDecimal("100"), 4, RoundingMode.HALF_UP);
        BigDecimal multiplier = BigDecimal.ONE.subtract(fraction);
        return amount -> amount.multiply(multiplier);
    }
}
