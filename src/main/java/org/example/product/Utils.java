package org.example.product;

import java.util.*;

public class Utils {
    public static <T extends Comparable<T>> List<T> sortedCopy(List<T> list) {
        List<T> copyList = new ArrayList<>(list);
        Collections.sort(copyList);
        return copyList;
    }

    //wybieram cene bez wysłlki - koszt wysyłki nie jest wartoscią towaru lezącego w magazynie

    public static double sumPrices(List<? extends Product> products) {
        double total = 0;
        for (Product product : products) {
            total += product.getNetPrice();
        }
        return total;
    }
}
