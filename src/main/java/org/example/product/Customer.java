package org.example.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Customer implements Identifiable<String> {
   private final String id;
   private final String name;

    @Override
    public String toString() {
        return "Customer{" +
                "ID='" + id + '\'' +
                ", NAME='" + name + '\'' +
                '}';
    }
}
