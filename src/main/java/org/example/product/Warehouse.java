package org.example.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Warehouse<T extends Identifiable<String>> {
    private final List<T> products = new ArrayList<>();

    public void add(T item) {
        products.add(item);
    }

    public List<T> findAll() {
        return List.copyOf(products);
    }

    //wybieram cene bez wysłlki - koszt wysyłki nie jest wartoscią towaru lezącego w magazynie


    public int count() {
        return products.size();
    }

    public Optional<T> findById(String id) {
        String normalId = id.trim();
        for (T product : products) {
            if (product.getId().equals(normalId)) {
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }

    public boolean remove(String id) {
        Optional<T> found = findById(id);
        if (found.isPresent()) {
            products.remove(found.get());
            return true;
        }
        return false;
    }
}
