package org.example.product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenericWarehouseDemo {
    public static void main(String[] args) {
        // punkt 4 metody generyczne

        List<String> original = List.of("BANAN", "JABŁKO", "ANANAS");
        List<Integer> numbers = List.of(5, 1, 3);
        List<FoodProduct> food = new ArrayList<>();

        food.add(new FoodProduct("L-01", "RYBA", 10, LocalDate.of(2026, 12, 12)));
        food.add(new FoodProduct("L-02", "RYBA", 12, LocalDate.of(2026, 12, 12)));

        System.out.println(Utils.sortedCopy(original));
        System.out.println(Utils.sortedCopy(numbers));
        System.out.println(Utils.sumPrices(food));

        // punkt 5 magazyny

        Warehouse<Product> productWarehouse = new Warehouse<>();

        productWarehouse.add(new FoodProduct("A-01", "RYBA", 2.99, LocalDate.of(2026, 12, 31)));
        productWarehouse.add(new FoodProduct("A-02", "JAJKO", 1.49, LocalDate.of(2026, 12, 31)));
        productWarehouse.add(new FoodProduct("A-03", "MASŁO", 7.29, LocalDate.of(2026, 12, 31)));

        Warehouse<Customer> customerWarehouse = new Warehouse<>();

        customerWarehouse.add(new Customer("B-01", "ANNA"));
        customerWarehouse.add(new Customer("B-02", "JAN"));
        customerWarehouse.add(new Customer("B-03", "NORBERT"));

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

        // customerWarehouse.add(new FoodProduct("A-05", "MASŁO", 7.29, LocalDate.of(2026, 12, 31)));
    }
}
