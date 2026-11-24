package org.example.orders;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class OrderService {
    private final Catalog catalog;
    private final PriceList priceList;
    private final OrderQueue orderQueue;

    public OrderService(Catalog catalog, PriceList priceList, OrderQueue orderQueue) {
        this.catalog = catalog;
        this.priceList = priceList;
        this.orderQueue = orderQueue;
    }

    public BigDecimal calculateTotal(Order o) {
        BigDecimal sum = BigDecimal.ZERO;
        for (String sku : o.skus()) {
            Product product = catalog.findBySku(sku)
                    .orElseThrow(() -> new IllegalArgumentException("BRAK PRODUKTU: " + sku));
            sum = sum.add(product.price());
        }
        BigDecimal discount = priceList.getDiscountFor( o.skus().size());
        BigDecimal factor = new BigDecimal("100").subtract(discount);
        return sum.multiply(factor).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
    }

    public List<String> findUnavailableSkus(Order o) {
        Set<String> available = catalog.getSkus();
        List<String> missing = new ArrayList<>();
        for (String sku : o.skus()) {
            if (!available.contains(sku)) {
                missing.add(sku);
            }
        }
        return missing;
    }

    public Map<String, Long> countProductsInPendingOrders() {
        Map<String, Long> result = new HashMap<>();
        for (Order o : orderQueue.getPending()) {
            for (String sku : o.skus()) {
                result.merge(sku, 1L, Long::sum);
            }
        }
        return result;
    }
}
