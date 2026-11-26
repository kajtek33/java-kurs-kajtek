package org.example.functional;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Predicate;

public class FunctionalDemo {

    private static void printNames(String title, List<Product> products) {
        System.out.print(title + " ");
        for (Product product : products) {
            System.out.print(product.name() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<Product> products = List.of(
                new Product("ABCD-123", "MYSZKA", new BigDecimal("49.99"), 10, "ELEKTRONIKA"),
                new Product("BCDE-123", "TABLET", new BigDecimal("1099.99"), 2, "ELEKTRONIKA"),
                new Product("AABB-123", "KLAWIATURA", new BigDecimal("599.99"), 0, "ELEKTRONIKA"),
                new Product("ACR-1", "SŁUCHAWKI", new BigDecimal("449.99"), 8, "ELEKTRONIKA")
        );
        Customer a = new Customer("ABC", "JAN", "JAN@PL", true, 2);
        Customer b = new Customer("DEF", "ANNA", "ANNA@PL", false, 1);

        // CZĘŚĆ 1: PREDICATE
        printNames("DOSTĘPNE I TANIE:", ProductFilters.filter(products, ProductFilters.IN_STOCK.and(ProductFilters.CHEAP)));
        printNames("TANIE ALBO DROGIE:", ProductFilters.filter(products, ProductFilters.CHEAP.or(ProductFilters.EXPENSIVE)));
        printNames("NIEDOSTĘPNE:", ProductFilters.filter(products, ProductFilters.IN_STOCK.negate()));

        Predicate<Product> isElectronics = p -> "ELEKTRONIKA".equals(p.category());
        printNames("DOSTĘPNE, ELEKTRONIKA, 100-1000:", ProductFilters.filter(products, ProductFilters.IN_STOCK.and(isElectronics)
                        .and(ProductFilters.priceBetween(new BigDecimal("100"), new BigDecimal("1000")))));
        printNames("POPRAWNE SKU:", ProductFilters.filter(products, ProductFilters.HAS_VALID_SKU));

        // CZĘŚĆ 2: FUNCTION
        System.out.println("FORMAT: " + PriceCalculator.FORMAT.apply(new BigDecimal("1234.5")));
        System.out.println("ETYKIETA: " + PriceCalculator.FULL_PRICE_LABEL.apply(products.get(0)));
        System.out.println("ETYKIETA, WYSYŁKA PRZED VAT: " + PriceCalculator.SHIPPING_THEN_VAT_LABEL.apply(products.get(0)));
        System.out.println("ETYKIETA, WYSYŁKA, VAT, RABAT: " + PriceCalculator.SHIPPING_VAT_DISCOUNT_LABEL.apply(products.get(0)));
        System.out.println("ETYKIETA, RABAT, WYSYŁKA, VAT: " + PriceCalculator.DISCOUNT_SHIPPING_VAT_LABEL.apply(products.get(0)));

        // CZĘŚĆ 3: CONSUMER I SUPPLIER
        Order order = OrderProcessor.createOrder(a, products, OrderProcessor.generateOrderId);
        OrderProcessor.buildPipeline(true, true).accept(order);
        System.out.println("AUDIT LOG: " + OrderProcessor.auditLog);

        // CZĘŚĆ 4: DISCOUNTPOLICY
        List<Product> items = List.of(
                new Product("TEST-100", "TEST1", new BigDecimal("200"), 1, "ELEKTRONIKA"),
                new Product("TEST-200", "TEST2", new BigDecimal("200"), 1, "ELEKTRONIKA"),
                new Product("TEST-300", "TEST3", new BigDecimal("200"), 1, "ELEKTRONIKA"),
                new Product("TEST-400", "TEST4", new BigDecimal("200"), 1, "ELEKTRONIKA"),
                new Product("TEST-500", "TEST5", new BigDecimal("200"), 1, "ELEKTRONIKA"),
                new Product("TEST-600", "TEST6", new BigDecimal("200"), 1, "ELEKTRONIKA")
        );
        System.out.println("RABAT, PREMIUM, 6 POZYCJI: " + DiscountPolicies.ALL.apply(new Order("X", a, items)));
        System.out.println("RABAT, PREMIUM, 5 POZYCJI: " + DiscountPolicies.ALL.apply(new Order("Y", a, items.subList(0, 5))));
        System.out.println("RABAT, BEZ PREMIUM, 6 POZYCJI: " + DiscountPolicies.ALL.apply(new Order("Z", b, items)));
    }
}
