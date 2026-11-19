package org.example.product;

import lombok.Getter;

@Getter
public class OutOfStockException extends ShopException {
    private final String sku;
    private final int requested;
    private final int available;

    public OutOfStockException(String sku, int requested, int available) {
        super("OUT OF STOCK: "+ sku + ", requested: " + requested + ", available: " + available);
        this.sku = sku;
        this.requested = requested;
        this.available = available;
    }
}
