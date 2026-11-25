package org.example.functional;

import java.util.List;

public record Order(String id, Customer customer, List<Product> items) {
}
