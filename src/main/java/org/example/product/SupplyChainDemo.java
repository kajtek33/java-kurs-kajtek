package org.example.product;


public class SupplyChainDemo {
    private static final String DEFAULT_CURRENCY = "PLN";

    public static void main(String[] args) {
        ProductWarehouse warehouse = new ProductWarehouse();

        Address address = new Address("KOKOSOWA 1", "Warszawa", "12-123");
        Supplier supplier = new Supplier("JAN", address, "INFO@S.PL");
        Supplier supplierBadEmail = new Supplier("JAN", null, "INFO-BRAK");
        Supplier supplierEmpty = new Supplier("JAN", null, null);

        Product e1 = new Electronics("E-1", "TABLET", Money.of("2000", DEFAULT_CURRENCY), 12, supplier);
        Product e2 = new Electronics("E-2", "LAPTOP", Money.of("4000", DEFAULT_CURRENCY), 12, null);
        Product e3 = new Electronics("E-3", "TABLET", Money.of("2000", DEFAULT_CURRENCY), 12, supplierBadEmail);
        Product e4 = new Electronics("E-4", "MYSZKA", Money.of("200", DEFAULT_CURRENCY), 12, supplierEmpty);
        Product e6 = new Electronics("E-6", "KLAWIATURA", Money.of("300", DEFAULT_CURRENCY), 12, supplier);

        ProductWarehouse small = new ProductWarehouse();
        small.add(e1);

        e1.setStock(5);
        e2.setStock(5);
        e3.setStock(2);
        e4.setStock(5);
        e6.setStock(0);

        warehouse.add(e1);
        warehouse.add(e2);
        warehouse.add(e3);
        warehouse.add(e4);
        warehouse.add(e6);

        Address krakow = new Address("RYNEK 1", "Kraków", "30-001");
        Supplier supplierKrakow = new Supplier("ADAM", krakow, "ADAM@S.PL");
        Product e7 = new Electronics("E-7", "MONITOR", Money.of("900", DEFAULT_CURRENCY), 12, supplierKrakow);
        warehouse.add(e7);

        String[] skus = {"E-1", "E-2", "E-3", "E-4", "E-6", "NIE-MA"};
        for (String sku : skus) {
            System.out.println("--- " + sku + " ---");
            System.out.println("name:    " + warehouse.findSupplierName(sku));
            System.out.println("email:   " + warehouse.findSupplierEmail(sku));
            System.out.println("city:    " + warehouse.findSupplierCity(sku));
            System.out.println("label:   " + warehouse.getSupplierLabel(sku));
            System.out.println("contact: " + warehouse.findEmailForContact(sku));
            System.out.println("shipping: " + warehouse.getShippingCity(sku));
        }
        System.out.println("cities: " + warehouse.collectSupplierCities());

        System.out.println("--- cheapest address ---");
        System.out.println("big:   " + warehouse.findAddressOfCheapest());
        System.out.println("small: " + small.findAddressOfCheapest());
        System.out.println("empty: " + new ProductWarehouse().findAddressOfCheapest());

        System.out.println("--- minStock ---");
        System.out.println("E-1, min 5: " + warehouse.findBySkuAndMinStock("E-1", 5));
        System.out.println("E-1, min 6: " + warehouse.findBySkuAndMinStock("E-1", 6));
        System.out.println("E-6, min 0: " + warehouse.findBySkuAndMinStock("E-6", 0));
        System.out.println("NIE-MA, min 1: " + warehouse.findBySkuAndMinStock("NIE-MA", 1));


        System.out.println(warehouse.getOrThrow("E-1"));
        try {
            warehouse.getOrThrow("NIE-MA");
        } catch (IllegalArgumentException e) {
            System.out.println("Błąd: " + e.getMessage());
        }
    }
}