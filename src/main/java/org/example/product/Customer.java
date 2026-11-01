package org.example.product;

public class Customer implements Identifiable<String> {
   private final String id;
   private final String name;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "ID='" + id + '\'' +
                ", NAME='" + name + '\'' +
                '}';
    }
}
