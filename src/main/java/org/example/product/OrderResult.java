package org.example.product;

import java.util.List;

public record OrderResult(List<Product> accepted, List<String> rejected) {
}
