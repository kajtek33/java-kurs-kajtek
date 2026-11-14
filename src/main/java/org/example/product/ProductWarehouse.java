package org.example.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductWarehouse extends  Warehouse<Product> {

    //Porównuje po cenie netto, bez wysyłki bo wysyłka nie jest kosztem produktu
    public Optional<Product> findCheapest() {
        List<Product> all = findAll();
        if (findAll().isEmpty()) {
            return Optional.empty();
        }

        Product cheapest = all.get(0);
        for (Product product : all) {
            if (cheapest.getNetPrice().isGreaterThan(product.getNetPrice())) {
                cheapest = product;
            }
        }
        return Optional.of(cheapest);
    }

    public Optional<String> findSupplierName(String sku) {
        return findById(sku)
                .flatMap(Product::findSupplier)
                .map(Supplier::name);
    }

    public Optional<String> findSupplierEmail(String sku) {
        return findById(sku)
                .flatMap(Product::findSupplier)
                .map(Supplier::contactEmail);
    }

    public Optional<String> findSupplierCity(String sku) {
        return findById(sku)
                .flatMap(Product::findSupplier)
                .flatMap(Supplier::findAddress)
                .map(address -> address.city().toUpperCase());
    }

    public String getSupplierLabel(String sku) {
        return findSupplierName(sku)
                .flatMap(name -> findSupplierCity(sku)
                .map(city -> "DOSTAWCA: " + name + " (" + city + ")"))
                .orElse("BRAK DANYCH DOSTAWCY");
    }

    public Optional<String> findEmailForContact(String sku) {
        return findById(sku)
                .filter(product -> product.getStock() > 0)
                .flatMap(Product::findSupplier)
                .flatMap(Supplier::findEmail)
                .filter(email -> email.contains("@"));
    }

    public Optional<Address> findAddressOfCheapest() {
        return findCheapest()
                .flatMap(Product::findSupplier)
                .flatMap(Supplier::findAddress);
    }

    public String getShippingCity(String sku) {
        return findSupplierCity(sku)
                .orElse("MAGAZYN CENTRALNY");
    }

    public Optional<Product> findBySkuAndMinStock(String sku, int minStock) {
        return findById(sku)
                .filter(product -> product.getStock() >= minStock);
    }

    public List<String> collectSupplierCities() {
        List<String> cities = new ArrayList<>();

        for (Product product : findAll()) {
            product.findSupplier()
                    .flatMap(Supplier::findAddress)
                    .map(Address::city)
                    .ifPresent(city -> {
                        if (!(cities.contains(city))) {
                            cities.add(city);
                        }
                    });
        }
        return cities;
    }

    public Product getOrThrow(String sku) {
        return findById(sku)
                .orElseThrow(() -> new IllegalArgumentException("NIE ZNALEZIONO PRODUKTU O SKU: " + sku));
    }
}
