package org.example.product;


public record InvoiceLine(ProductDTO product, int quantity) {

    public Money netValue() {
        Money netValue = product.netPrice().multiply(quantity);
        return netValue;
    }

    public Money vatValue() {
        Money vatValue = netValue().applyPercent(product.category().getVatRate());
        return vatValue;
    }

    public Money grossValue() {
        Money grossValue = netValue().add(vatValue());
        return grossValue;
    }
}
