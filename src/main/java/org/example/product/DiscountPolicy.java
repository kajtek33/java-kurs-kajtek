package org.example.product;

public interface DiscountPolicy {
    double apply (double price);
    String getName();

    default boolean appliesTo(Product p) {
       return true;
    }
}
