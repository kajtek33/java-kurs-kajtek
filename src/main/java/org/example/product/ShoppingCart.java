package org.example.product;

import lombok.RequiredArgsConstructor;

import java.util.*;
@RequiredArgsConstructor
public class ShoppingCart {
    private final Map<Product, Integer> products = new LinkedHashMap<>();
    private final DiscountPolicy policy;
    private static final String DEFAULT_CURRENCY = "PLN";

    public void add(Product p, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("WARTOŚĆ NIE MOŻE BYĆ RÓWNA 0 LUB MNIEJSZA");
        }

        if (p == null) {
            throw new IllegalArgumentException("PRODUKT NIE MOŻE BYĆ PUSTY");
        }
        int currentQuantity = products.getOrDefault(p, 0);
        products.put(p, quantity + currentQuantity);
    }

    public Money getTotal() {
        Money total = Money.zero(DEFAULT_CURRENCY);

        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity= entry.getValue();
            Money unitPrice;
            if (policy.appliesTo(product)) {
                unitPrice = policy.apply(product.getNetPrice());
            } else {
                unitPrice = product.getNetPrice();
            }
            total = total.add(unitPrice.multiply(quantity));
        }
        return total;
    }

    public void remove(String sku) {
        Product toRemove = null;
        String normalized = sku.trim().toUpperCase();

        for (Product product : products.keySet()) {
            if (product.getSku().equals(normalized)) {
                toRemove = product;
            }
        }
        if (toRemove != null) {
            products.remove(toRemove);
        }
    }

    public String generateReceipt() {
        String receipt = "PARAGON (" + policy.getName() + ")\n";

        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity= entry.getValue();
            Money unitPrice;
            if (policy.appliesTo(product)) {
                unitPrice = policy.apply(product.getNetPrice());
            } else {
                unitPrice = product.getNetPrice();
            }
            receipt += product.getName() + " x" + quantity + " po " + unitPrice + "\n";
        }

        receipt += "RAZEM: " + getTotal() + "\n";

        return receipt;
    }

    public List<Product> getSortedBy(Comparator<Product> comparator) {
        List<Product> sorted = new ArrayList<>(products.keySet());
        sorted.sort(comparator);
        return sorted;
    }
}
