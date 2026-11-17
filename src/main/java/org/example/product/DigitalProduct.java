package org.example.product;

import lombok.Getter;

@Getter
public class DigitalProduct extends Product {
    private final String downloadUrl;

    public DigitalProduct(String sku, String name, Money netPrice, String downloadUrl) {
        super(sku, name, netPrice, Category.DIGITAL);
        this.downloadUrl = downloadUrl;
    }

    @Override
    public Money getShippingCost() {
        return Money.zero(getNetPrice().currency());
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (PLIK DO POBRANIA)";
    }

    @Override
    public final String getLabel() {
        return getName() + " [" + getCategory().getDisplayName() + "] — " + getNetPrice() + " LINK DO POBRANIA: " + downloadUrl;
    }
}
