package org.example.product;

public record ProductInput(@NotBlank String sku, @NotBlank @MaxLength(value = 50) String name, @Positive double netPrice, Category category) {
}
