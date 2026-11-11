package org.example.product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ShopDemo {

    private static final String DEFAULT_CURRENCY = "PLN";

    public static void main(String[] args) {

        List<Product> products = new ArrayList<>();
        ShoppingCart cart1 = new ShoppingCart(new NoDiscount());
        ShoppingCart cart2 = new ShoppingCart(new ElectronicsOnlyDiscount());
        ShoppingCart cart3 = new ShoppingCart(new PercentageDiscount(15));
        ShoppingCart cart4 = new ShoppingCart(new FixedAmountDiscount(Money.of("800", DEFAULT_CURRENCY)));

        products.add(new Electronics("C01", "PRALKA", Money.of("1499", DEFAULT_CURRENCY), 18));
        products.add(new Electronics("C02", "LAMPKA", Money.of("799", DEFAULT_CURRENCY), 6));
        products.add(new FoodProduct("D01", "POMIDOR", Money.of("2.99", DEFAULT_CURRENCY), LocalDate.of(2026, 12, 31)));
        products.add(new FoodProduct("D02", "PAPRYKA", Money.of("3.49", DEFAULT_CURRENCY), LocalDate.of(2026, 12, 31)));
        products.add(new DigitalProduct("E01", "KURS", Money.of("1099", DEFAULT_CURRENCY), "kurs.pl"));

        for (Product p : products) {
            cart1.add(p, 1);
        }

        for (Product p : products) {
            cart2.add(p, 1);
        }

        for (Product p : products) {
            cart3.add(p, 1);
        }

        for (Product p : products) {
            cart4.add(p, 1);
        }

        cart1.remove("D01");
        cart2.remove("  d02  ");
        cart3.remove("XYZ");

        System.out.println(cart1.generateReceipt());
        System.out.println(cart2.generateReceipt());
        System.out.println(cart3.generateReceipt());
        System.out.println(cart4.generateReceipt());
    }
}
