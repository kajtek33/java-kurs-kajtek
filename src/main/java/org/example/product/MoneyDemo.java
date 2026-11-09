package org.example.product;


public class MoneyDemo {
    private static final String DEFAULT_CURRENCY = "PLN";

    public static void main(String[] args) {

        Invoice invoice = new Invoice();

        invoice.addLine(new InvoiceLine(new ProductDTO("A01", "RYBA", Money.of("12.99", DEFAULT_CURRENCY), Category.FOOD), 2));
        invoice.addLine(new InvoiceLine(new ProductDTO("A02", "TABLET", Money.of("1399", DEFAULT_CURRENCY), Category.ELECTRONICS), 1));
        invoice.addLine(new InvoiceLine(new ProductDTO("A03", "STAR-WARS", Money.of("19.99", DEFAULT_CURRENCY), Category.BOOKS), 1));
        invoice.addLine(new InvoiceLine(new ProductDTO("A04", "WINO", Money.of("17.49", DEFAULT_CURRENCY), Category.ALCOHOL), 4));
        invoice.addLine(new InvoiceLine(new ProductDTO("A05", "KARMA DLA PSA", Money.of("19.99", DEFAULT_CURRENCY), Category.FOOD), 3));

        System.out.println(invoice.render());

        // inne zadanie

        System.out.println(Money.of("3999", "pln"));
        System.out.println(Money.zero("eur"));
        System.out.println(Money.of("1234567.555", "PLN"));
        System.out.println(Money.of("19.99", "PLN").multiply(3));
        System.out.println(Money.of("100", "PLN").applyPercent(23));
        System.out.println(Money.of("3", "PLN").subtract(Money.of("10", "PLN")));
        System.out.println(Money.of("10", "PLN").add(Money.of("5", "EUR")));


    }
}
