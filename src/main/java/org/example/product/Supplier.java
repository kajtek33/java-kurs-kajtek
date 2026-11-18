package org.example.product;

import java.util.Optional;

public record Supplier(String name, Address address, String contactEmail) {
    public Optional<Address> findAddress() {
        return Optional.ofNullable(address());
    }

    public Optional<String> findEmail() {
        return Optional.ofNullable(contactEmail());
    }
}
