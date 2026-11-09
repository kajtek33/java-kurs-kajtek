package org.example.product;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Category {
    ELECTRONICS("Elektronika", 23, false),
    FOOD("Spożywcze", 5, false),
    ALCOHOL("Alkohol", 23, true),
    BOOKS("Książki", 5, false);

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

    public Money calculateGross(Money net) {
        Money vat = net.applyPercent(vatRate);
        return net.add(vat);
    }

    public static List<Category> withoutAgeCheck() {
       return Arrays.stream(values()).filter(c -> !c.requiresAgeVerification).collect(Collectors.toList());
    }
}
