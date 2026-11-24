package org.example.product;

import lombok.Getter;

@Getter
public class InvalidPriceException extends ShopException {
    private final String price;

    public InvalidPriceException(String price) {
        super("INVALID PRICE: " + price);
        this.price = price;
    }
}
