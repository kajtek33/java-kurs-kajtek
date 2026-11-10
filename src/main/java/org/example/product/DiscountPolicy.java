package org.example.product;

public interface DiscountPolicy {
    Money apply(Money price);
    String getName();

    default boolean appliesTo(Product p) {
       return true;
    }
}
