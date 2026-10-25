package org.example.product;

public class TestProduct {
    public static void main(String[] args) {
        //1
        Product p = new Product("  elec-001  ","   Laptop   ", 3999);
        p.addTag(" ELEKTRONIKA ");
        p.addTag(" PROMOCJA ");
        System.out.println(p);
        System.out.println("[" + p.getSku() + "]");
        System.out.println("[" + p.getName() + "]");
        //2
        p.applyDiscount(10);
        System.out.println("PO RABACIE: " + p);
        p.raisePriceBy(10);
        System.out.println("PO PODWYŻCE: " + p);
        //3
        try {
            p.getTags().add("HACK");
            System.out.println("TO SIĘ NIE WYPISZE");
        } catch (UnsupportedOperationException e) {
            System.out.println("BŁĄD: NIE MOŻNA ZMIENIAĆ LISTY TAGÓW Z ZEWNĄTRZ");
        }
        //4
        try {
            new Product("", "Laptop", 100);
            System.out.println("TO SIĘ NIE WYPISZE");
        } catch (IllegalArgumentException e) {
            System.out.println("BŁĄD: " + e.getMessage());
        }

        try {
            new Product("ELEC-002", "", 100);
            System.out.println("TO SIĘ NIE WYPISZE");
        } catch (IllegalArgumentException e) {
            System.out.println("BŁĄD: " + e.getMessage());
        }

        try {
            new Product("ELEC-003", "Myszka", -5);
            System.out.println("TO SIĘ NIE WYPISZE");
        } catch (IllegalArgumentException e) {
            System.out.println("BŁĄD: " + e.getMessage());
        }
        //5
        //Dodatkowa walidacja metod biznesowych
        try {
            p.applyDiscount(0);
        } catch (IllegalArgumentException e) {
            System.out.println("BŁĄD: " + e.getMessage());
        }

        try {
            p.applyDiscount(100);
        } catch (IllegalArgumentException e) {
            System.out.println("BŁĄD: " + e.getMessage());
        }

        try {
            p.applyDiscount(Double.NaN);
        } catch (IllegalArgumentException e) {
            System.out.println("BŁĄD: " + e.getMessage());
        }

        try {
            p.raisePriceBy(-5);
            System.out.println("TO SIĘ NIE WYPISZE");
        } catch (IllegalArgumentException e) {
            System.out.println("BŁĄD: " + e.getMessage());
        }

        //6
        // Dodatkowy wydruk na celu pokazania, ze mimo nie udanych prob nic sie nie zmienilo w kwocie
        System.out.println(p);
    }
}
