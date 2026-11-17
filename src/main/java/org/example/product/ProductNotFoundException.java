package org.example.product;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends ShopException {
    private final String sku;

    public ProductNotFoundException(String sku) {
        super("PRODUCT NOT FOUND: " + sku);
        this.sku = sku;
    }
}
