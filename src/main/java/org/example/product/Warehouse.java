package org.example.product;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    private final List<Product> products = new ArrayList<>();

    public void add(Product product) {
        products.add(product);
    }

    public List<Product> getAll() {
        return List.copyOf(products);
    }

    //wybieram cene bez wysłlki - koszt wysyłki nie jest wartoscią towaru lezącego w magazynie

    public double getTotalValue() {
        double total = 0;
        for (Product product : products) {
            total += product.getNetPrice();
        }
        return total;
    }

    public int count() {
        return products.size();
    }

    public Product findBySku(String sku) {
        String normalSku = sku.trim().toUpperCase();
        for (Product product : products) {
            if (product.getSku().equals(normalSku)) {
                return product;
            }
        }
        return null;
    }
}
