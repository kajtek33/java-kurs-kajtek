package org.example.product;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public abstract class Product {
     static final double DEFAULT_VAT_RATE = 0.23;
     private static int createdCount = 0;

     private final String sku;
     private final String name;
     private double netPrice;
     private final List<String> tags;

     protected Product(String sku, String name, double netPrice) {
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
          createdCount++;
     }

     protected Product(Product other) {
          this(other.sku, other.name, other.netPrice);
          tags.addAll(other.tags);
     }

//     public static Product fromCsvLine (String line) {
//          if (line == null) {
//               throw new IllegalArgumentException("WIERSZ NIE MOŻE BYĆ NULL");
//          }
//
//          String[] parts = line.split(";");
//          if (parts.length != 3) {
//               throw new IllegalArgumentException("ZŁY WIERSZ: " + line);
//          }
//
//          double number;
//          try {
//               number = Double.parseDouble(parts[2]);
//          } catch (NumberFormatException e) {
//               throw new IllegalArgumentException("ZŁY WIERSZ: " + line);
//          }
//          return of(parts[0], parts[1], number);
//     }

     public static int getCreatedCount() {
          return createdCount;
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
     public List<String> getTags() {
          return List.copyOf(tags);
     }

     public double getGrossPrice() {
          return netPrice * (1 + DEFAULT_VAT_RATE);
     }

     private String formatPrice(double value) {
          return String.format(Locale.forLanguageTag("pl-PL"), "%.2f", value);
     }

     public String getDescription() {
          return name + " — " + formatPrice(netPrice) + " zł";
     }

     public abstract double getShippingCost();

     public abstract String getCategory();

     // CENA W ZADANIU OZNACZA CENE NETTO
     public final double getTotalPrice() {
          return netPrice + getShippingCost();
     }

     public final String getLabel() {
          return name + " [" + getCategory() + "] — " + formatPrice(netPrice) + " ZŁ (Z WYSYŁKĄ " + formatPrice(getTotalPrice()) + " ZŁ)";
     }

     public static void setCreatedCount(int createdCount) {
          Product.createdCount = createdCount;
     }

     public void addTag(String tag) {
          if (tag == null || tag.isBlank()) {
               throw new IllegalArgumentException("TAG NIE MOŻE BYĆ PUSTY");
          }
          String normalizedTag = tag.trim().toLowerCase();
          tags.add(normalizedTag);
     }

     public Product addTags(String... tags) {
          for (String tag : tags) {
               addTag(tag);
          }
          return this;
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
     public boolean equals(Object o) {
          if (this == o) return true;
          if (o == null || getClass() !=o.getClass()) return false;
          Product other = (Product) o;
          return sku.equals(other.sku);
     }

     @Override
     public int hashCode() {
          return Objects.hash(sku);
     }

     @Override
     public String toString() {
          return sku + " " + name + " - " + String.format (Locale.forLanguageTag("pl-PL"), "NETTO %.2f / BRUTTO %.2f ZŁ", netPrice, getGrossPrice());
     }
}
