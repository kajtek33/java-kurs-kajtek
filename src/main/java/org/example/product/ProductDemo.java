package org.example.product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductDemo {

    private static final String DEFAULT_CURRENCY = "PLN";

    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();

        products.add(new Electronics("A01", "TABLET", Money.of("2500", DEFAULT_CURRENCY), 12));
        products.add(new Electronics("A02", "SŁUCHAWKI", Money.of("1800", DEFAULT_CURRENCY), 18));
        products.add(new FoodProduct("A03", "RYBA", Money.of("21.99", DEFAULT_CURRENCY), LocalDate.of(2026, 12, 31)));
        products.add(new FoodProduct("A04", "MIĘSO", Money.of("40.99", DEFAULT_CURRENCY), LocalDate.of(2026, 12, 31)));
        products.add(new DigitalProduct("A05", "KURS", Money.of("899", DEFAULT_CURRENCY),"plik.html"));

        Money cartTotal = Money.zero(DEFAULT_CURRENCY);

        for (Product product : products) {
            System.out.println(product.getDescription() + " | RAZEM: " + product.getTotalPrice());
            cartTotal = cartTotal.add(product.getTotalPrice());
        }
        System.out.println("SUMA KOSZYKA: " + cartTotal);
    }
}
