package org.example.orders;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;

public class Catalog {
    private List<Product> insertionOrder = new ArrayList<>();
    private Set<String> categories = new HashSet<>();
    private Map<String, Product> bySku = new HashMap<>();

    public void add(Product p) {
        insertionOrder.add(p);
        categories.add(p.category());
        bySku.put(p.sku(), p);
    }

    public Optional<Product> findBySku(String sku) {
        return Optional.ofNullable(bySku.get(sku));
    }

    public List<Product> findByCategory(String cat) {
        List<Product> result = new ArrayList<>();
        for (Product p : insertionOrder) {
            if (p.category().equals(cat)) {
                result.add(p);
            }
        }
        result.sort(Comparator.comparing(Product::price));
        return result;
    }

    public Set<String> getCategories() {
        return new HashSet<>(categories);
    }

    public List<Product> sortedByName() {
        List<Product> copy = new ArrayList<>(insertionOrder);
        copy.sort(Comparator.naturalOrder());
        return copy;
    }

    public List<Product> sortedByPriceDesc() {
        List<Product> copy = new ArrayList<>(insertionOrder);
        copy.sort(Comparator.comparing(Product::price).reversed());
        return copy;
    }

    public int size() {
        return insertionOrder.size();
    }

    public void removeExpired(Predicate<Product> condition) {
        insertionOrder.removeIf(condition);
    }

    public Map<String, List<Product>> groupByCategory() {
        Map<String, List<Product>> result = new HashMap<>();
        for (Product p : insertionOrder) {
            result.computeIfAbsent(p.category(), k-> new ArrayList<>()).add(p);
        }
        return result;
    }

    public Map<String, Integer> countByCategory() {
        Map<String, Integer> result = new HashMap<>();
        for (Product p : insertionOrder) {
            result.merge(p.category(), 1, Integer::sum);
        }
        return result;
    }

    public Map<String, BigDecimal> totalValueByCategory() {
        Map<String, BigDecimal> result = new HashMap<>();
        for (Product p : insertionOrder) {
            result.merge(p.category(), p.price(), BigDecimal::add);
        }
        return result;
    }

    public Optional<Product> findCheapest() {
        if (insertionOrder.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(Collections.min(insertionOrder, Comparator.comparing(Product::price)));
    }

    public Optional<Product> findMostExpensive() {
        if (insertionOrder.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(Collections.max(insertionOrder, Comparator.comparing(Product::price)));
    }

    public Set<String> getSkus() {
        return new HashSet<>(bySku.keySet());
    }
}
