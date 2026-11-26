package org.example.sales;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class DemoSalesReports {
    public static void main(String[] args) {

        Product laptop = new Product("A1", "LAPTOP", "ELEKTRONIKA", new BigDecimal("3000.00"), 5, 4.5);
        Product mouse = new Product("A2", "MYSZKA", "ELEKTRONIKA", new BigDecimal("50.00"), 0, 0);
        Product apple = new Product("B1", "JABŁKO", "OWOCE", new BigDecimal("3.00"), 13, 5);
        Product melon = new Product("B2", "MELON", "OWOCE", new BigDecimal("14.00"), 2, 4.1);
        Product jacket = new Product("C1", "KURTKA", "UBRANIA", new BigDecimal("450.00"), 3, 3.9);
        Product shirt = new Product("C2", "KOSZULA", "UBRANIA", new BigDecimal("199.00"), 8, 4.4);

        ProductCatalog catalog = new ProductCatalog(List.of(laptop, mouse, apple, melon, jacket, shirt));

        OrderLine line1 = new OrderLine(laptop, 2);
        OrderLine line2 = new OrderLine(mouse, 1);
        OrderLine line3 = new OrderLine(apple, 9);
        OrderLine line4 = new OrderLine(melon, 2);
        OrderLine line5 = new OrderLine(jacket, 1);
        OrderLine line6 = new OrderLine(shirt, 5);

        Order order1 = new Order("01", "ANNA", LocalDate.of(2026, 11, 11), List.of(line1, line3, line4));
        Order order2 = new Order("02", "JAN", LocalDate.of(2026, 5, 16), List.of(line2, line5));
        Order order3 = new Order("03", "MARCIN", LocalDate.of(2026, 1, 21), List.of(line1, line2, line6));
        Order order4 = new Order("04", "LENA", LocalDate.of(2026, 12, 20), List.of(line5));

        OrderReports reports = new OrderReports(List.of(order1, order2, order3, order4));

        System.out.println(catalog.availableSorted());
        System.out.println(catalog.namesInCategory("ELEKTRONIKA"));
        System.out.println(reports.orderTotal(order1));

        System.out.println(catalog.mostExpensive());
        System.out.println(catalog.bestRatedInCategory("UBRANIA"));
        System.out.println(catalog.bestRatedInCategory("NIE MA TAKIEJ"));
        System.out.println(catalog.page(0, 2));
        System.out.println(catalog.page(1, 2));
        System.out.println(catalog.page(10, 2));
        System.out.println(catalog.allRated());
        System.out.println(catalog.hasOutOfStock());
        System.out.println(catalog.categoryList());

        System.out.println(catalog.byCategory());
        System.out.println(catalog.countByCategory());
        System.out.println(catalog.averageRatingByCategory());
        System.out.println(catalog.productNamesByCategory());
        System.out.println(catalog.mostExpensiveByCategory());
        System.out.println(catalog.splitByAvailability());
        System.out.println(catalog.stockValueByCategory());
        System.out.println(catalog.stockStatistics());
        System.out.println(catalog.priceStatsByCategory());
        System.out.println(catalog.textReport());

        System.out.println(reports.allOrderedProducts());
        System.out.println(reports.orderTotal(order3));
        System.out.println(reports.revenueByCustomer());
        System.out.println(reports.unitsSoldByProduct());
        System.out.println(reports.top3BestSelling());
        System.out.println(reports.largestOrder());

        new OrderReports(List.of()).largestOrder();
        OrderReports emptyReports = new OrderReports(List.of());
        System.out.println(emptyReports.largestOrder());
        System.out.println(emptyReports.top3BestSelling());
        System.out.println(emptyReports.allOrderedProducts());
    }
}
