package org.example.sales;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class OrderReports {
    private final List<Order> orders;

    public OrderReports(List<Order> orders) {
        this.orders = orders;
    }

    public List<Product> allOrderedProducts() {
        return orders.stream()
                .flatMap(o -> o.lines().stream())
                .map(OrderLine::product)
                .distinct()
                .toList();
    }

    public BigDecimal orderTotal(Order order) {
        return order.lines().stream()
                .map(line -> line.product().price().multiply(BigDecimal.valueOf(line.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<String, BigDecimal> revenueByCustomer() {
        return orders.stream()
                .collect(Collectors.groupingBy(Order::customer, Collectors.reducing(BigDecimal.ZERO, o -> orderTotal(o), BigDecimal::add)));
    }

    public Map<String, Integer> unitsSoldByProduct() {
        return orders.stream()
                .flatMap(o -> o.lines().stream())
                .collect(Collectors.groupingBy(line -> line.product().sku(), Collectors.summingInt(OrderLine::quantity)));
    }

    public List<Product> top3BestSelling() {
        Map<Product, Integer> units = orders.stream()
                .flatMap(o -> o.lines().stream())
                .collect(Collectors.groupingBy(line -> line.product(), Collectors.summingInt(OrderLine::quantity)));

        return units.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();
    }

    public Optional<Order> largestOrder() {
        return orders.stream()
                .max(Comparator.comparing(o -> orderTotal(o)));
    }
}
