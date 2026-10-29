package org.example.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ShoppingCart {
    private final List<Product> products = new ArrayList<>();
    private final DiscountPolicy policy;

    public ShoppingCart(DiscountPolicy policy) {
        this.policy = policy;
    }

    public void add(Product p) {
        products.add(p);
    }

    public double getTotal() {
        double totalSum = 0;

        for (Product product : products) {
            if (policy.appliesTo(product)) {
               totalSum += policy.apply(product.getNetPrice());
            } else {
               totalSum +=  product.getNetPrice();
            }
        }
        return totalSum;
    }

    public void remove(String sku) {
        Product toRemove = null;
        String normalized = sku.trim().toUpperCase();

        for (Product product : products) {
            if (product.getSku().equals(normalized)) {
                toRemove = product;
            }
        }
        if (toRemove != null) {
            products.remove(toRemove);
        }
    }

    public String getReceipt() {
        String receipt = "PARAGON (" + policy.getName() + ")\n";

        for (Product product : products) {
            double price = 0;

            if (policy.appliesTo(product)) {
                price += policy.apply(product.getNetPrice());
            } else {
                price +=  product.getNetPrice();
            }

            receipt += product.getName() + " " + String.format(Locale.forLanguageTag("pl-PL"), "%.2f", price) + "\n";
        }

        receipt += "RAZEM: " + String.format(Locale.forLanguageTag("pl-PL"), "%.2f", getTotal()) + "\n";

        return receipt;
    }
}
