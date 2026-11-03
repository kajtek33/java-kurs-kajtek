package org.example.product;

import java.util.List;

public record Receipt(String cartId, List<ProductDTO> items) {
}
