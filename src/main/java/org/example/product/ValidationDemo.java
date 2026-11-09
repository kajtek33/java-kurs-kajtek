package org.example.product;

public class ValidationDemo {

    private static final String DEFAULT_CURRENCY = "PLN";

    public static void main(String[] args) {

        System.out.println("POPRAWNE:");
        ProductDTO ok = new ProductDTO("A01", "LAPTOP", Money.of("1999", DEFAULT_CURRENCY), Category.ELECTRONICS);
        System.out.println(ProductValidator.validate(ok));


        System.out.println("TRZY BŁĘDY NARAZ:");
        String tooLongName = "a".repeat(51);
        ProductInput wrong = new ProductInput("", tooLongName, -5, Category.ELECTRONICS);
        System.out.println(ProductValidator.validate(wrong));
    }
}
