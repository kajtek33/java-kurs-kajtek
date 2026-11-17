package org.example.product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class GenericWarehouseDemo {

    private static final String DEFAULT_CURRENCY = "PLN";

    public static void main(String[] args) {
        // punkt 4 metody generyczne

        List<String> original = List.of("BANAN", "JABŁKO", "ANANAS");
        List<Integer> numbers = List.of(5, 1, 3);
        List<FoodProduct> food = new ArrayList<>();

        food.add(new FoodProduct("L-01", "RYBA", Money.of("10", DEFAULT_CURRENCY), LocalDate.of(2026, 12, 12)));
        food.add(new FoodProduct("L-02", "RYBA", Money.of("12", DEFAULT_CURRENCY), LocalDate.of(2026, 12, 12)));

        System.out.println(Utils.sortedCopy(original));
        System.out.println(Utils.sortedCopy(numbers));
        System.out.println(Utils.sumPrices(food, DEFAULT_CURRENCY));

        // punkt 5 magazyny

        Warehouse<Product> productWarehouse = new Warehouse<>();

        productWarehouse.add(new FoodProduct("C-01", "RYBA", Money.of("2.99", DEFAULT_CURRENCY), LocalDate.of(2026, 12, 31)));
        productWarehouse.add(new FoodProduct("B-02", "JAJKO", Money.of("1.49", DEFAULT_CURRENCY), LocalDate.of(2026, 12, 31)));
        productWarehouse.add(new FoodProduct("A-03", "MASŁO", Money.of("7.29", DEFAULT_CURRENCY), LocalDate.of(2026, 12, 31)));

        Warehouse<Customer> customerWarehouse = new Warehouse<>();

        customerWarehouse.add(new Customer("A-01", "ANNA"));
        customerWarehouse.add(new Customer("H-02", "JAN"));
        customerWarehouse.add(new Customer("D-03", "NORBERT"));

        System.out.println(productWarehouse.count());
        System.out.println(customerWarehouse.count());

        Optional<Customer> foundCustomer = customerWarehouse.findById("B-02");
        if (foundCustomer.isPresent()) {
            System.out.println(foundCustomer.get());
        }

        Optional<Customer> foundCustomer1 = customerWarehouse.findById("B-04");
        if (foundCustomer1.isPresent()) {
            System.out.println(foundCustomer1.get());
        } else {
            System.out.println("BRAK KLIENTA O ID 'B-04'");
        }

        Optional<Product> foundProduct = productWarehouse.findById("A-02");
        if (foundProduct.isPresent()) {
            System.out.println(foundProduct.get().getLabel());
        }

        Optional<Product> foundProduct1 = productWarehouse.findById("A-04");
        if (foundProduct1.isPresent()) {
            System.out.println(foundProduct1.get().getLabel());
        } else {
            System.out.println("BRAK PRODUKTU O ID 'A-04'");
        }

        //PD-10 MONEY TESTY
        System.out.println(Utils.sortedCopy(productWarehouse.findAll()));
        Product egg = productWarehouse.findById("B-02").orElseThrow();
        Product fish = productWarehouse.findById("C-01").orElseThrow();
        Product butter = productWarehouse.findById("A-03").orElseThrow();
        System.out.println(egg.getLabel());

        ShoppingCart shoppingCart = new ShoppingCart(new FixedAmountDiscount(Money.of("1", DEFAULT_CURRENCY)));
        shoppingCart.add(egg, 2);
        shoppingCart.add(egg, 4);
        shoppingCart.add(fish, 5);
        shoppingCart.add(butter, 2);
        System.out.println(shoppingCart.generateReceipt());

        System.out.println(shoppingCart.getSortedBy(Comparator.naturalOrder()));
        System.out.println(shoppingCart.getSortedBy(Comparator.comparing(Product::getName)));

        DigitalProduct ebook = new DigitalProduct("A01", "E-BOOK", Money.of("1000", DEFAULT_CURRENCY), "ebook@123");
        System.out.println(ebook.getLabel());
    }
}
