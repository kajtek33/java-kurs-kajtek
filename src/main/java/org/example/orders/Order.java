package org.example.orders;

import java.util.List;

public record Order(String id, String customer, int priority, List<String> skus) {
}
