package org.example.product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<Product> products = new ArrayList<>();
    private final DiscountPolicy policy;
    private static final String DEFAULT_CURRENCY = "PLN";

    public ShoppingCart(DiscountPolicy policy) {
        this.policy = policy;
    }

    public void add(Product p) {
        products.add(p);
    }

    public Money getTotal() {
        Money total = Money.zero(DEFAULT_CURRENCY);

        for (Product product : products) {
            if (policy.appliesTo(product)) {
                total = total.add(policy.apply(product.getNetPrice()));
            } else {
                total = total.add(product.getNetPrice());
            }
        }
        return total;
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
            Money price;

            if (policy.appliesTo(product)) {
                price = policy.apply(product.getNetPrice());
            } else {
                price =  product.getNetPrice();
            }

            receipt += product.getName() + " " + price + "\n";
        }

        receipt += "RAZEM: " + getTotal() + "\n";

        return receipt;
    }
}
