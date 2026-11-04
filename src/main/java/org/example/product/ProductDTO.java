package org.example.product;

public record ProductDTO(@NotBlank String sku, @NotBlank @MaxLength(value = 50) String name, @Positive double netPrice, Category category) {
    public ProductDTO {

        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("SKU NIE MOŻE BYĆ PUSTY");
        }
        sku = sku.trim().toUpperCase();

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("NAME NIE MOŻE BYĆ PUSTE");
        }

        if (!(netPrice > 0)) {
            throw new IllegalArgumentException("KWOTA MUSI BYĆ WIĘKSZA OD 0");
        }

        if (category == null) {
            throw new IllegalArgumentException("KATEGORIA NIE MOŻE BYĆ PUSTA");
        }
    }

    public double grossPrice() {
        return category.calculateGross(netPrice);
    }

    public static ProductDTO from(Product p) {
        return new ProductDTO(p.getSku(), p.getName(), p.getNetPrice(), p.getCategory());
    }

    public static ProductDTO fromCsvLine(String line) {
          if (line == null) {
               throw new IllegalArgumentException("WIERSZ NIE MOŻE BYĆ NULL");
          }

          String[] parts = line.split(";");
          if (parts.length != 4) {
               throw new IllegalArgumentException("ZŁY WIERSZ: " + line);
          }

          double number;
          try {
               number = Double.parseDouble(parts[2]);
          } catch (NumberFormatException e) {
               throw new IllegalArgumentException("ZŁY WIERSZ: " + line);
          }

          Category category;
          try {
              category = Category.valueOf(parts[3]);
          } catch (IllegalArgumentException e) {
              throw new IllegalArgumentException("ZŁY WIERSZ: " + line);
          }

          return new ProductDTO(parts[0], parts[1], number, category);
    }
}
