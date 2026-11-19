package org.example.product;

import lombok.Getter;

@Getter
public class InvalidPriceException extends ShopException {
    private final Money price;

    public InvalidPriceException(Money price) {
        super("INVALID PRICE: " + price);
        this.price = price;
    }
}
