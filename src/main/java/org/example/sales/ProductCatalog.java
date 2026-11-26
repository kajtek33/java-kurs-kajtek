package org.example.sales;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class ProductCatalog {
    private final List<Product> products;

    public ProductCatalog(List<Product> products) {
        this.products = products;
    }

    public List<Product> availableSorted() {
        return products.stream()
                .filter(p -> p.stock() > 0)
                .sorted(Comparator.comparing(Product::price))
                .toList();
    }

    public List<String> namesInCategory(String category) {
        return products.stream()
                .filter(p -> p.category().equals(category))
                .map(Product::name)
                .sorted()
                .toList();
    }

    public Optional<Product> mostExpensive() {
        return products.stream()
                .max(Comparator.comparing(Product::price));
    }

    public Optional<Product> bestRatedInCategory(String category) {
        return products.stream()
                .filter(p -> p.category().equals(category))
                .max(Comparator.comparingDouble(Product::rating));
    }

    public List<Product> page(int pageNumber, int pageSize) {
        return products.stream()
                .sorted(Comparator.comparing(Product::name))
                .skip(pageNumber * pageSize)
                .limit(pageSize)
                .toList();
    }

    public boolean allRated() {
        return products.stream()
                .allMatch(p -> p.rating() > 0);
    }

    public boolean hasOutOfStock() {
        return products.stream()
                .anyMatch(p -> p.stock() <= 0);
    }

    public String categoryList() {
        return products.stream()
                .map(Product::category)
                .distinct()
                .collect(Collectors.joining(", ", "[", "]"));
    }

    public Map<String, List<Product>> byCategory() {
        return products.stream()
                .collect(Collectors.groupingBy(Product::category));
    }

    public Map<String, Long> countByCategory() {
        return products.stream()
                .collect(Collectors.groupingBy(Product::category, Collectors.counting()));
    }

    public Map<String, Double> averageRatingByCategory() {
        return products.stream()
                .collect(Collectors.groupingBy(Product::category, Collectors.averagingDouble(Product::rating)));
    }

    public Map<String, List<String>> productNamesByCategory() {
        return products.stream()
                .collect(Collectors.groupingBy(Product::category, Collectors.mapping(Product::name, Collectors.toList())));
    }

    public Map<String, Optional<Product>> mostExpensiveByCategory() {
        return products.stream()
                .collect(Collectors.groupingBy(Product::category, Collectors.maxBy(Comparator.comparing(Product::price))));
    }

    public Map<Boolean, List<Product>> splitByAvailability() {
        return products.stream()
                .collect(Collectors.partitioningBy(p -> p.stock() > 0));
    }

    public TreeMap<String, BigDecimal> stockValueByCategory() {
        return products.stream()
                .collect(Collectors.groupingBy(Product::category, TreeMap::new, Collectors.reducing(BigDecimal.ZERO, p -> p.price().multiply(BigDecimal.valueOf(p.stock())), BigDecimal::add)));
    }

    public IntSummaryStatistics stockStatistics() {
        return products.stream()
                .mapToInt(Product::stock)
                .summaryStatistics();
    }

    public Map<String, DoubleSummaryStatistics> priceStatsByCategory() {
        return products.stream()
                .collect(Collectors.groupingBy(Product::category, Collectors.summarizingDouble(p -> p.price().doubleValue())));
    }

    public String textReport() {
        return byCategory().entrySet().stream()
                .map(entry -> {
                    List<Product> list = entry.getValue();

                    int count = list.size();
                    double avg = list.stream().mapToDouble(Product::rating).average().orElse(0);
                    String mostExpensive = list.stream().max(Comparator.comparing(Product::price)).map(Product::name).orElse("BRAK");

                    return String.format(Locale.US, "%s: %d PRODUKTÓW, ŚREDNIA OCENA %.1f, NAJDROŻSZY: %s", entry.getKey(), count, avg, mostExpensive);
                })
                .collect(Collectors.joining("\n"));
    }
}
