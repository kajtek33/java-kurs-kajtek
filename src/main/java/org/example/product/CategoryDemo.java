package org.example.product;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class CategoryDemo {
    public static void main(String[] args) {
        List<String> lines = List.of(
                "ELEC-001;LAPTOP;3999.00;ELECTRONICS",
                "ELEC-002;TABLET;2499.00;ELECTRONICS",
                "ELED-001;BURAK;2.00;FOOD",
                "ELEW-001;WINO;29.00;ALCOHOL",
                "ELER-001;STAR-WARS;45.00;BOOKS"
        );

        List<ProductDTO> products = new ArrayList<>();
        for (String line : lines) {
            products.add(ProductDTO.fromCsvLine(line));
        }

        System.out.println("RAPORT Z CENAMI BRUTTO:");
        for (ProductDTO product : products) {
            System.out.printf( "%s %.2f zł%n",product.name(), product.grossPrice());
        }

        Map<Category, Integer> count = new EnumMap<>(Category.class);
        for (ProductDTO product : products) {
            int current = count.getOrDefault(product.category(), 0);
            count.put(product.category(), current + 1);
        }
        System.out.println("ILOŚĆ SZTUK W KATEGORIACH:");
        System.out.println(count);

        System.out.println("PRODUKTY WYMAGAJĄCE WERYFIKACJI WIEKU:");
        for (ProductDTO product : products) {
            if(product.category().isRequiresAgeVerification()) {
                System.out.println(product.name());
            }
        }
    }
}
