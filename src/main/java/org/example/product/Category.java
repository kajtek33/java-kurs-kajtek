package org.example.product;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Category {
    ELECTRONICS("Elektronika", 0.23, false),
    FOOD("Spożywcze", 0.05, false),
    ALCOHOL("Alkohol", 0.23, true),
    BOOKS("Książki", 0.05, false);

    private final String displayName;
    private final double vatRate;
    private final boolean requiresAgeVerification;

    Category(String displayName, double vatRate, boolean requiresAgeVerification) {
        this.displayName = displayName;
        this.vatRate = vatRate;
        this.requiresAgeVerification = requiresAgeVerification;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getVatRate() {
        return vatRate;
    }

    public boolean isRequiresAgeVerification() {
        return requiresAgeVerification;
    }

    public double calculateGross(double net) {
       return net + net * vatRate;
    }

    public static List<Category> withoutAgeCheck() {
       return Arrays.stream(values()).filter(c -> !c.requiresAgeVerification).collect(Collectors.toList());
    }
}
