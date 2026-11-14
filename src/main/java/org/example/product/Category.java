package org.example.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum Category {
    ELECTRONICS("Elektronika", 23, false),
    FOOD("Spożywcze", 5, false),
    ALCOHOL("Alkohol", 23, true),
    BOOKS("Książki", 5, false),
    DIGITAL("Cyfrowe", 23, false);

    private final String displayName;
    private final double vatRate;
    private final boolean requiresAgeVerification;

    public Money calculateGross(Money net) {
        Money vat = net.applyPercent(vatRate);
        return net.add(vat);
    }

    public static List<Category> withoutAgeCheck() {
       return Arrays.stream(values()).filter(c -> !c.requiresAgeVerification).collect(Collectors.toList());
    }
}
