package org.example.product;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ProductValidator {
    public static List<String> validate(Object obj) {
        List<String> errors = new ArrayList<>();

        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(NotBlank.class)) {
                try {
                    Object value = field.get(obj);
                    if (   value == null || (value instanceof String s && s.isBlank())) {
                       errors.add("POLE " + field.getName() + " NIE MOŻE BYĆ PUSTE");
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }

            if (field.isAnnotationPresent(Positive.class)) {
               try {
                   Object value = field.get(obj);
                   boolean positive = ((value instanceof Number n) &&  n.doubleValue() > 0 || (value instanceof Money m && m.amount().signum() > 0));
                       if (!positive) {
                           errors.add("POLE " + field.getName() + " MUSI BYĆ WIĘKSZE OD 0");
                       }

               } catch (IllegalAccessException e) {
                   throw new RuntimeException(e);
               }
            }

            if (field.isAnnotationPresent(MaxLength.class)) {
                try {
                    Object value = field.get(obj);
                    int limit = field.getAnnotation(MaxLength.class).value();
                    if (value instanceof String s && s.length() > limit) {
                        errors.add("POLE " + field.getName() + " NIE MOŻE MIEĆ WIĘCEJ NIŻ " + limit + " ZNAKÓW");
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return errors;
    }
}
