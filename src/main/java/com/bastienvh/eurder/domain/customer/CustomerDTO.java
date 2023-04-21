package com.bastienvh.eurder.domain.customer;

import java.util.Objects;
import java.util.UUID;

public record CustomerDTO(UUID id, String firstName, String lastName, String email, Address address, String phoneNumber) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDTO that = (CustomerDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(address, that.address) && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, address, phoneNumber);
    }
}
