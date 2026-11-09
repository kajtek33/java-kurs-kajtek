package org.example.product;

import java.util.*;

public class Utils {
    public static <T extends Comparable<T>> List<T> sortedCopy(List<T> list) {
        List<T> copyList = new ArrayList<>(list);
        Collections.sort(copyList);
        return copyList;
    }

    //wybieram cene bez wysłlki - koszt wysyłki nie jest wartoscią towaru lezącego w magazynie

    public static Money sumPrices(List<? extends Product> products, String currency) {
        Money total = Money.zero(currency);
        for (Product product : products) {
            total = total.add(product.getNetPrice());
        }
        return total;
    }
}
