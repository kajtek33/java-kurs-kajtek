package org.example.orders;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class PriceList {
    private TreeMap<Integer, BigDecimal> discountThresholds = new TreeMap<>();

    public void addThreshold(int minQty, BigDecimal discount) {
        discountThresholds.put(minQty, discount);
    }

    public BigDecimal getDiscountFor(int quantity) {
        return Optional.ofNullable(discountThresholds.floorEntry(quantity))
                .map(Map.Entry::getValue)
                .orElse(BigDecimal.ZERO);
    }

    // "DO PODANEJ LICZBY" = Włącznie, więc headMap(max, true)
    public Map<Integer, BigDecimal> thresholdsUpTo(int max) {
        return new TreeMap<>(discountThresholds.headMap(max, true));
    }

    public Optional<Integer> nextThresholdAfter(int quantity) {
        return Optional.ofNullable(discountThresholds.higherKey(quantity));
    }
}
