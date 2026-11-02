package org.example.product;

import java.util.HashSet;
import java.util.Set;

//public class TestProduct {
//    public static void main(String[] args) {
//        //1
//        Product p = Product.of("  elec-001  ","   Laptop   ", 3999);
//        p.addTag(" ELEKTRONIKA ");
//        p.addTag(" PROMOCJA ");
//        System.out.println(p);
//        System.out.println("[" + p.getSku() + "]");
//        System.out.println("[" + p.getName() + "]");
//        //2
//        p.applyDiscount(10);
//        System.out.println("PO RABACIE: " + p);
//        p.raisePriceBy(10);
//        System.out.println("PO PODWYŻCE: " + p);
//        //3
//        try {
//            p.getTags().add("HACK");
//            System.out.println("TO SIĘ NIE WYPISZE");
//        } catch (UnsupportedOperationException e) {
//            System.out.println("BŁĄD: NIE MOŻNA ZMIENIAĆ LISTY TAGÓW Z ZEWNĄTRZ");
//        }
//        //4
//        try {
//            Product.of("", "Laptop", 100);
//            System.out.println("TO SIĘ NIE WYPISZE");
//        } catch (IllegalArgumentException e) {
//            System.out.println("BŁĄD: " + e.getMessage());
//        }
//
//        try {
//            Product.of("ELEC-002", "", 100);
//            System.out.println("TO SIĘ NIE WYPISZE");
//        } catch (IllegalArgumentException e) {
//            System.out.println("BŁĄD: " + e.getMessage());
//        }
//
//        try {
//            Product.of("ELEC-003", "Myszka", -5);
//            System.out.println("TO SIĘ NIE WYPISZE");
//        } catch (IllegalArgumentException e) {
//            System.out.println("BŁĄD: " + e.getMessage());
//        }
//        //5
//        //Dodatkowa walidacja metod biznesowych
//        try {
//            p.applyDiscount(0);
//        } catch (IllegalArgumentException e) {
//            System.out.println("BŁĄD: " + e.getMessage());
//        }
//
//        try {
//            p.applyDiscount(100);
//        } catch (IllegalArgumentException e) {
//            System.out.println("BŁĄD: " + e.getMessage());
//        }
//
//        try {
//            p.applyDiscount(Double.NaN);
//        } catch (IllegalArgumentException e) {
//            System.out.println("BŁĄD: " + e.getMessage());
//        }
//
//        try {
//            p.raisePriceBy(-5);
//            System.out.println("TO SIĘ NIE WYPISZE");
//        } catch (IllegalArgumentException e) {
//            System.out.println("BŁĄD: " + e.getMessage());
//        }
//
//        //6
//        // Dodatkowy wydruk na celu pokazania, ze mimo nie udanych prob nic sie nie zmienilo w kwocie
//        System.out.println(p);
//
//        System.out.println();
//        System.out.println("---HOMEWORK - PRODUCT CONTINUATION---");
//        System.out.println();
//
////1 - trzy rozne sposoby
//
//        Product p1 = Product.of("ELEC-003", "MYSZKA", 999);
//        Product p2 = Product.of("ELEC-004", "KLAWIATURA", 1499).addTags("elektronika", "promocja");
//        Product p3 = Product.fromCsvLine("ELEC-005;SŁUCHAWKI;1299");
//
//        System.out.println(p1);
//        System.out.println(p2.getTags());
//        System.out.println(p3);
////2 - kopia
//
//        Product copy = new Product(p2);
//        System.out.println(copy);
//        System.out.println(copy.getTags().equals(p2.getTags()));
//
////3 - licznik utworzonych obiektow
//
//        System.out.println(Product.getCreatedCount());
//
////4 - hashSet z rozmiarem
//
//        Set<Product> set1 = new HashSet<>();
//        set1.add(p1);
//        set1.add(p2);
//        set1.add(p3);
//        System.out.println(set1.size());
//
////5  - kopia i wypis z rozmiarem
//
//        set1.add(copy);
//        System.out.println(set1.size());
//
////6 - equals i ==
//
//        System.out.println(copy.equals(p2));
//        System.out.println(copy == p2);
//
////7 - fromCsvLine
//
//        try {
//            Product.fromCsvLine("ELEC-001;Laptop");
//            System.out.println("TO SIĘ NIE WYPISZE");
//        } catch (IllegalArgumentException e) {
//            System.out.println("BŁĄD: " + e.getMessage());
//        }
//
//    }
//}
