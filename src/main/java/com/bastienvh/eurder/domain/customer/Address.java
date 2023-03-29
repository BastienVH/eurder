package com.bastienvh.eurder.domain.customer;

import org.apache.commons.lang3.Validate;

import java.util.Objects;

public record Address (String street, String number, String city, int postalCode) {
    public Address {
        Objects.requireNonNull(street, "street can't be null");
        Objects.requireNonNull(number, "number can't be null");
        Objects.requireNonNull(city, "city can't be null");
        Validate.exclusiveBetween(999, 10000, postalCode, "postal code is not valid");
    }

}
