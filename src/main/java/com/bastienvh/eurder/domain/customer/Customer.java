package com.bastienvh.eurder.domain.customer;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Customer {
    @Id
    private UUID id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "address_id")
    private Address address;
    @Column
    private String phoneNumber;

    public Customer(String firstName, String lastName, String email, Address address, String phoneNumber) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    protected Customer() {

    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
