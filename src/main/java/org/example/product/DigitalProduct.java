package org.example.product;

public class DigitalProduct extends Product {
    private final String downloadUrl;

    public DigitalProduct(String sku, String name, Money netPrice, String downloadUrl) {
        super(sku, name, netPrice, Category.ELECTRONICS);

        this.downloadUrl = downloadUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    @Override
    public Money getShippingCost() {
        return Money.zero(getNetPrice().currency());
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (PLIK DO POBRANIA)";
    }
}
