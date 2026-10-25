package org.example.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Product {
     private final String sku;
     private final String name;
     private double netPrice;
     private final List<String> tags;

     public Product(String sku, String name, double netPrice) {
          if (sku == null || sku.isBlank()) {
               throw new IllegalArgumentException("SKU NIE MOŻE BYĆ PUSTY");
          }
          this.sku = sku.trim().toUpperCase();

          if (name == null || name.isBlank()) {
               throw new IllegalArgumentException("NAME NIE MOŻE BYĆ PUSTE");
          }
          this.name = name.trim();

          if (!(netPrice > 0)) {
               throw new IllegalArgumentException("KWOTA MUSI BYĆ WIĘKSZA OD 0");
          }
          this.netPrice = netPrice;
          this.tags = new ArrayList<>();
     }

     public String getSku() {
          return sku;
     }

     public String getName() {
          return name;
     }

     public double getNetPrice() {
          return netPrice;
     }
     public void addTag(String tag) {
          if (tag == null || tag.isBlank()) {
               throw new IllegalArgumentException("TAG NIE MOŻE BYĆ PUSTY");
          }
          String normalizedTag = tag.trim().toLowerCase();
          tags.add(normalizedTag);
     }

     public List<String> getTags() {
          return List.copyOf(tags);
     }

     public void raisePriceBy(double percent) {
          if (!(percent > 0)) {
               throw new IllegalArgumentException("PROCENT PODWYŻKI MUSI BYĆ WIĘKSZY OD 0");
          }

          double newPrice = netPrice * (1 + percent / 100);
          this.netPrice = newPrice;
     }

     public void applyDiscount(double percent) {
          if (!(percent > 0 && percent < 100)) {
               throw new IllegalArgumentException("ZNIŻKA MUSI MIEŚCIĆ SIĘ W ZAKRESIE");
          }

          double newPrice = netPrice * (1 - percent / 100);
          if (newPrice < 0.01) {
               throw new IllegalArgumentException("CENA NIE MOŻE ZEJŚĆ PONIŻEJ 0,01");
          }
          this.netPrice = newPrice;
     }


     @Override
     public String toString() {
          return sku + " " + name + " — " + String.format (Locale.forLanguageTag("pl-PL"), "%.2f", netPrice) + " zł " + tags;
     }
}
