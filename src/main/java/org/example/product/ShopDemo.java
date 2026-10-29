package org.example.product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ShopDemo {
    public static void main(String[] args) {

        List<Product> products = new ArrayList<>();
        ShoppingCart cart1 = new ShoppingCart(new NoDiscount());
        ShoppingCart cart2 = new ShoppingCart(new ElectronicsOnlyDiscount());
        ShoppingCart cart3 = new ShoppingCart(new PercentageDiscount(15));
        ShoppingCart cart4 = new ShoppingCart(new FixedAmountDiscount(800));

        products.add(new Electronics("C01", "PRALKA", 1499, 18));
        products.add(new Electronics("C02", "LAMPKA", 799, 6));
        products.add(new FoodProduct("D01", "POMIDOR", 2.99, LocalDate.of(2026, 12, 31)));
        products.add(new FoodProduct("D02", "PAPRYKA", 3.49, LocalDate.of(2026, 12, 31)));
        products.add(new DigitalProduct("E01", "KURS", 1099, "kurs.pl"));

        for (Product p : products) {
            cart1.add(p);
        }

        for (Product p : products) {
            cart2.add(p);
        }

        for (Product p : products) {
            cart3.add(p);
        }

        for (Product p : products) {
            cart4.add(p);
        }

        cart1.remove("D01");
        cart2.remove("  d02  ");
        cart3.remove("XYZ");

        System.out.println(cart1.getReceipt());
        System.out.println(cart2.getReceipt());
        System.out.println(cart3.getReceipt());
        System.out.println(cart4.getReceipt());
    }
}
