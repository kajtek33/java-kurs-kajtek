package org.example.product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

public record Money(BigDecimal amount, String currency) {
    public Money {
        if (amount == null)  {
            throw new IllegalArgumentException("POLE 'AMOUNT' NIE MOŻE BYĆ PUSTE");

        }
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(amount + " NIE MOŻE BYĆ MNIEJSZE OD ZERA");
        }

        if (currency == null) {
            throw new IllegalArgumentException("POLE 'CURRENCY' NIE MOŻE BYĆ PUSTE");
        }

        if (currency.isBlank()) {
            throw new IllegalArgumentException("POLE 'CURRENCY' NIE MOŻE BYĆ PUSTE");
        }
        currency = currency.trim().toUpperCase();
        amount = amount.setScale(2, RoundingMode.HALF_UP);
    }

    public Money add(Money other) {
        if (!other.currency.equals(currency)) {
            throw new IllegalArgumentException("RÓŻNE WALUTY: " + other.currency + " : " + currency);
        }
        BigDecimal add = other.amount.add(amount);
        return new Money(add, currency);
    }

    public Money subtract(Money other) {
        BigDecimal difference = amount.subtract(other.amount);

        if (!other.currency.equals(currency)) {
            throw new IllegalArgumentException("RÓŻNE WALUTY:" + other.currency + " : " + currency);
        }

        if (difference.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("WYNIK NIE MOŻE BYĆ UJEMNY");
        }
        return new Money(difference, currency);
    }

    public Money multiply(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("ILOŚĆ NIE MOŻE BYĆ UJEMNA: " + quantity);
        }
        BigDecimal quantityDecimal = BigDecimal.valueOf(quantity);
        BigDecimal product = amount.multiply(quantityDecimal);
        return new Money(product, currency);
    }

    public Money applyPercent(double percent) {
        if (percent < 0) {
            throw new IllegalArgumentException("PROCENT NIE MOŻE BYĆ UJEMNY: " + percent);
        }
        BigDecimal percentDecimal = BigDecimal.valueOf(percent);
        BigDecimal hundred = BigDecimal.valueOf(100);
        BigDecimal amountTimesPercent = percentDecimal.multiply(amount);
        BigDecimal percentOfAmount = amountTimesPercent.divide((hundred),2, RoundingMode.HALF_UP);
        return new Money(percentOfAmount, currency);
    }

    public boolean isGreaterThan(Money other) {
        if (!other.currency.equals(currency)) {
            throw new IllegalArgumentException("RÓŻNE WALUTY");
        }
        return amount.compareTo(other.amount) > 0;
    }

    public static Money zero(String currency) {
       return new Money(BigDecimal.ZERO, currency);
    }

    public static Money of(String amount, String currency) {
        BigDecimal parsedAmount = new BigDecimal(amount);
        return new Money(parsedAmount, currency);
    }

    @Override
    public String toString() {
        return String.format(Locale.forLanguageTag("pl-PL"), "%,.2f %s", amount, currency);
    }
}
