package org.example.product;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Invoice {
    private final List<InvoiceLine> lines = new ArrayList<>();
    private static final String DEFAULT_CURRENCY = "PLN";

    public void addLine(InvoiceLine line) {
        if (line == null) {
            throw new IllegalArgumentException("BRAKUJE POZYCJI");
        }
        lines.add(line);
    }

    public Money getTotalNet() {
        Money total = Money.zero(DEFAULT_CURRENCY);
        for (InvoiceLine line : lines) {
            total = total.add(line.netValue());
        }
        return total;
    }

    public Money getTotalVat() {
        Money total = Money.zero(DEFAULT_CURRENCY);
        for (InvoiceLine line : lines) {
            total = total.add(line.vatValue());
        }
        return total;
    }

    public Money getTotalGross() {
        Money total = Money.zero(DEFAULT_CURRENCY);
        for (InvoiceLine line : lines) {
            total = total.add(line.grossValue());
        }
        return total;
    }

    public Map<Category, Money> getVatSummary() {
        Map<Category, Money> vatByCategory = new EnumMap<>(Category.class);

        for (InvoiceLine line : lines) {
            Category category = line.product().category();
            Money current = vatByCategory.getOrDefault(category, Money.zero(DEFAULT_CURRENCY));
            vatByCategory.put(category, current.add(line.vatValue()));
        }
        return vatByCategory;
    }

    public String render() {
        StringBuilder stringBuilder = new StringBuilder();
        String beginningInvoice = """
                ---FAKTURA---
                
                """;
        stringBuilder.append(beginningInvoice);

        String rowFormat = "%-20s %6s %16s %16s %16s%n";
        stringBuilder.append(String.format(rowFormat, "NAZWA", "ILOŚĆ", "NETTO", "VAT", "BRUTTO"));

        for (InvoiceLine line : lines) {
            stringBuilder.append(String.format(rowFormat, line.product().name(), line.quantity(), line.netValue(), line.vatValue(), line.grossValue()));
        }

        String vatFormat = "%-20s %16s%n";
        stringBuilder.append("VAT WEDŁUG KATEGORII:\n");

        for (Map.Entry<Category, Money> entry : getVatSummary().entrySet()) {
            stringBuilder.append(String.format(vatFormat, entry.getKey().getDisplayName(), entry.getValue()));
        }

        stringBuilder.append(String.format(vatFormat, "RAZEM NETTO", getTotalNet()));
        stringBuilder.append(String.format(vatFormat, "RAZEM VAT", getTotalVat()));
        stringBuilder.append(String.format(vatFormat, "RAZEM BRUTTO", getTotalGross()));

        return stringBuilder.toString();
    }
}
