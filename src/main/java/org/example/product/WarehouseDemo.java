package org.example.product;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class WarehouseDemo {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();

        warehouse.add(new Electronics("B01", "TABLET", 2500, 12));
        warehouse.add(new Electronics("B02", "SŁUCHAWKI", 1800, 18));
        warehouse.add(new FoodProduct("B03", "RYBA", 21.99, LocalDate.of(2026, 12, 31)));
        warehouse.add(new FoodProduct("B04", "MIĘSO", 40.99, LocalDate.of(2026, 12, 31)));
        warehouse.add(new DigitalProduct("B05", "KURS", 899, "plik.html"));

        System.out.println("LICZBA PRODUKTÓW: " + warehouse.count());

        Optional<Product> found = warehouse.findById("B01");
        System.out.println(found.get().getLabel());

        String sku = "b03";

        Optional<Product> found1= warehouse.findById(sku);

        if (found1 == null) {
            System.out.println("BRAK PRODUKTU O SKU: " + sku);
        } else {
            System.out.println(found1.get().getLabel());
        }

        List<Product> all = warehouse.findAll();
        System.out.println("getAll(): " + all.size() + " PRODUKTÓW");

        for (Product p : all) {
            System.out.println(p.getName());
        }

//        System.out.println("WARTOŚĆ MAGAZYNU: " + String.format(Locale.forLanguageTag("pl-PL"), "%.2f", warehouse.getTotalValue()) + " ZŁ");

        try {
            all.clear();
            System.out.println("TO SIĘ NIE WYPISZE");
        } catch (UnsupportedOperationException e) {
            System.out.println("NIE MOŻNA ZMIENIAĆ LISTY Z ZEWNĄTRZ");
        }
        System.out.println("PO PRÓBIE: " + warehouse.count());
    }
}
