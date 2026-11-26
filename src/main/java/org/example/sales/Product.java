package org.example.sales;

import java.math.BigDecimal;

public record Product(String sku, String name, String category, BigDecimal price, int stock, double rating) {
}
