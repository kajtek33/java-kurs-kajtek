package org.example.product;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Product implements Identifiable<String>, Comparable<Product> {
     static final double DEFAULT_VAT_RATE = 23;
     private static int createdCount = 0;

     @EqualsAndHashCode.Include
     private final String sku;
     private final String name;
     private Money netPrice;
     private final List<String> tags;
     private final Category category;

     protected Product(String sku, String name, Money netPrice, Category category) {
          if (sku == null || sku.isBlank()) {
               throw new IllegalArgumentException("SKU NIE MOŻE BYĆ PUSTY");
          }
          this.sku = sku.trim().toUpperCase();

          if (name == null || name.isBlank()) {
               throw new IllegalArgumentException("NAME NIE MOŻE BYĆ PUSTE");
          }
          this.name = name.trim();

          if (netPrice == null) {
               throw new IllegalArgumentException("BRAK CENY");
          }

          if (!(netPrice.isGreaterThan(Money.zero(netPrice.currency())))) {
               throw new IllegalArgumentException("KWOTA MUSI BYĆ WIĘKSZA OD 0");
          }
          this.netPrice = netPrice;
          this.tags = new ArrayList<>();

          if (category == null) {
               throw new IllegalArgumentException("KATEGORIA NIE MOŻE BYĆ PUSTA");
          }
          this.category = category;
          createdCount++;
     }

     protected Product(Product other) {
          this(other.sku, other.name, other.netPrice, other.category);
          tags.addAll(other.tags);
     }

     public static int getCreatedCount() {
          return createdCount;
     }

     public List<String> getTags() {
          return List.copyOf(tags);
     }

     public Money getGrossPrice() {
          Money vat = netPrice.applyPercent(category.getVatRate());
          return netPrice.add(vat);
     }

     public String getDescription() {
          return name + " — " + netPrice;
     }

     public abstract Money getShippingCost();


     // CENA W ZADANIU OZNACZA CENE NETTO
     public final Money getTotalPrice() {
          Money total = netPrice.add(getShippingCost());
          return total;
     }

     public abstract String getLabel();

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
          Money raise = netPrice.applyPercent(percent);
          Money newPrice = netPrice.add(raise);
          this.netPrice = newPrice;
     }

     public void applyDiscount(double percent) {
          if (!(percent > 0 && percent < 100)) {
               throw new IllegalArgumentException("ZNIŻKA MUSI MIEŚCIĆ SIĘ W ZAKRESIE");
          }
          Money discount = netPrice.applyPercent(percent);
          Money newPrice =  netPrice.subtract(discount);
          Money minimum = Money.of("0.01", netPrice.currency());

          if (minimum.isGreaterThan(newPrice)) {
               throw new IllegalArgumentException("CENA NIE MOŻE ZEJŚĆ PONIŻEJ 0,01");
          }
          this.netPrice = newPrice;
     }

     @Override
     public String toString() {
          return sku + " " + name + " - " + String.format ("NETTO %s / BRUTTO %s", netPrice, getGrossPrice());
     }

     @Override
     public String getId() {
          return sku;
     }

     @Override
     public int compareTo(Product o) {
          return this.sku.compareTo(o.sku);
     }
}
