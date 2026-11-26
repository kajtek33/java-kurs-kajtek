package org.example.functional;

public record Customer(String id, String name, String email, boolean premium, int orderCount) {
}
