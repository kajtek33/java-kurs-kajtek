package org.example.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductDemo {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();

        products.add(new Electronics("A01", "TABLET", 2500, 12));
        products.add(new Electronics("A02", "SŁUCHAWKI", 1800, 18));
        products.add(new FoodProduct("A03", "RYBA", 21.99, "2026-11-20"));
        products.add(new FoodProduct("A04", "MIĘSO", 40.99, "2026-11-21"));
        products.add(new DigitalProduct("A05", "KURS", 899, "plik.html"));

        double cartTotal = 0;

        for (Product product : products) {
            System.out.println(product.getDescription() + " | RAZEM: " + String.format(Locale.forLanguageTag("pl-PL"), "%.2f", product.getTotalPrice()) + " ZŁ");
            cartTotal+= product.getTotalPrice();
        }
        System.out.println("SUMA KOSZYKA: " + String.format(Locale.forLanguageTag("pl-PL"), "%.2f", cartTotal) + " ZŁ");
    }
}
