package com.bastienvh.eurder.domain;

import java.util.UUID;

public class Customer {
    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Address address;
    private final String phoneNumber;

    public Customer(String firstName, String lastName, String email, Address address, String phoneNumber) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
