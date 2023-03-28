package com.bastienvh.eurder.domain;

import org.apache.commons.lang3.Validate;

public class Address {
    private final String street;
    private final String number;
    private final String city;
    private final int postalCode;

    public Address(String street, String number, String city, int postalCode) {
        Validate.notNull(street, "street can't be null");
        Validate.notNull(number, "number can't be null");
        Validate.notNull(city, "city can't be null");
        Validate.exclusiveBetween(999, 10000, postalCode, "postal code is not valid");
        this.street = street;
        this.number = number;
        this.city = city;
        this.postalCode = postalCode;
    }

}
