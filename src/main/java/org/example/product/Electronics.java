package org.example.product;

public class Electronics extends Product {
    private final int warrantyMonths;

    public Electronics(String sku, String name, Money netPrice, int warrantyMonths) {
        super(sku, name, netPrice, Category.ELECTRONICS);

        this.warrantyMonths = warrantyMonths;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    @Override
    public Money getShippingCost() {
        Money freeShipping = Money.of("2000", getNetPrice().currency());
        if (getNetPrice().isGreaterThan(freeShipping)) {
            return Money.zero(getNetPrice().currency());
        }
        return Money.of("19.99", getNetPrice().currency());
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (GWARANCJA " + warrantyMonths + " MIES.)";
    }
}
