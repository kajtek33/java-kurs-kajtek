package org.example.orders;

import java.math.BigDecimal;
import java.util.List;

public class OrderDemo {
    public static void main(String[] args) {
        Catalog catalog = new Catalog();
        catalog.add(new Product("A01", "BANAN", new BigDecimal("2.99"), "OWOCE"));
        catalog.add(new Product("A02", "JABŁKO", new BigDecimal("0.99"), "OWOCE"));
        catalog.add(new Product("A03", "GRUSZKA", new BigDecimal("3.49"), "OWOCE"));
        catalog.add(new Product("A04", "ANANAS", new BigDecimal("12.99"), "OWOCE"));
        catalog.add(new Product("B01", "MARCHEW", new BigDecimal("1.49"), "WARZYWA"));
        catalog.add(new Product("B02", "PAPRYKA", new BigDecimal("3.99"), "WARZYWA"));
        catalog.add(new Product("B03", "BROKUŁ", new BigDecimal("4.99"), "WARZYWA"));
        catalog.add(new Product("C01", "RODZYNKI", new BigDecimal("15.99"), "BAKALIE"));

        PriceList priceList = new PriceList();
        priceList.addThreshold(2, new BigDecimal("5"));
        priceList.addThreshold(4, new BigDecimal("10"));
        priceList.addThreshold(6, new BigDecimal("15"));

        OrderQueue orderQueue = new OrderQueue();
        OrderService orderService = new OrderService(catalog, priceList, orderQueue);

        orderQueue.submit(new Order("Z1", "ANNA", 2, List.of("A01", "B02")));
        orderQueue.submit(new Order("Z2", "PIOTR", 5, List.of("A04", "B01")));
        orderQueue.submit(new Order("Z3", "JAN", 7, List.of("A03", "B03")));
        orderQueue.submit(new Order("Z4", "KASIA", 3, List.of("A01", "C01", "B03", "A04")));
        orderQueue.submit(new Order("Z5", "MARCIN", 9, List.of("A02", "C01")));

        System.out.println("PRZETWORZONO: " + orderQueue.processNext());
        System.out.println("PRZETWORZONO: " + orderQueue.processNext());
        System.out.println("PRZETWORZONO: " + orderQueue.processNext());
        System.out.println("COFNIĘTO: " + orderQueue.undoLast());

        System.out.println("\n=== KATALOG ===");
        System.out.println("GRUPY WEDŁUG KATEGORII: " + catalog.groupByCategory());
        System.out.println("LICZBA WEDŁUG KATEGORII: " + catalog.countByCategory());
        System.out.println("WARTOŚĆ WEDŁUG KATEGORII: " + catalog.totalValueByCategory());
        System.out.println("NAJTAŃSZY: " + catalog.findCheapest());
        System.out.println("NAJDROŻSZY: " + catalog.findMostExpensive());
        System.out.println("WEDŁUG NAZWY: " + catalog.sortedByName());
        System.out.println("WEDŁUG CENY MALEJĄCO: " + catalog.sortedByPriceDesc());

        System.out.println("\n=== CENNIK ===");
        System.out.println("RABAT DLA 1 POZYCJI: " + priceList.getDiscountFor(1));
        System.out.println("RABAT DLA 3 POZYCJI: " + priceList.getDiscountFor(3));
        System.out.println("RABAT DLA 7 POZYCJI: " + priceList.getDiscountFor(7));
        System.out.println("PROGI DO 4: " + priceList.thresholdsUpTo(4));
        System.out.println("NASTĘPNY PRÓG PO 4: " + priceList.nextThresholdAfter(4));

        System.out.println("\n=== KOLEJKA ===");
        System.out.println("HISTORIA: " + orderQueue.getHistory());
        System.out.println("STATUSY: " + orderQueue.groupByStatus());
        System.out.println("KLIENCI Z ZAMÓWIENIAMI W KOLEJCE: " + orderQueue.findCustomersWithPendingOrders());

        System.out.println("\n=== SERWIS ===");
        Order twoPosition = new Order("T1", "TEST", 1, List.of("A01", "B02"));
        Order fourPosition = new Order("T2", "TEST", 1, List.of("A01", "A02", "B01", "B02"));
        Order withShortages = new Order("T3", "TEST", 1, List.of("A01", "XXX", "YYY"));

        System.out.println("SUMA (2 POZYCJE): " + orderService.calculateTotal(twoPosition));
        System.out.println("SUMA (4 POZYCJE): " + orderService.calculateTotal(fourPosition));
        System.out.println("BRAKUJĄCE SKU: " + orderService.findUnavailableSkus(withShortages));
        System.out.println("PRODUKTY W KOLEJCE: " + orderService.countProductsInPendingOrders());
    }
}
