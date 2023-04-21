package com.bastienvh.eurder.domain.customer;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_id_seq")
    @SequenceGenerator(sequenceName = "address_id_seq", allocationSize = 1, name = "address_id_seq")
    private int id;
    @Column
    @NotBlank(message = "street name not provided")
    private String streetName;
    @Column
    @NotBlank(message = "number not provided")
    private String houseNumber;
    @Column
    @Min(value = 1000, message = "postal code not provided or below minimum threshold (1000)")
    @Max(value = 9999, message = "postal code is above maximum threshold (9999)")
    private int postalCode;

    public Address(String streetName,
                   String houseNumber,
                   int postalCode) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
    }

    protected Address() {
    }

    public int getId() {
        return id;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public int getPostalCode() {
        return postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id == address.id && postalCode == address.postalCode && Objects.equals(streetName, address.streetName) && Objects.equals(houseNumber, address.houseNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, streetName, houseNumber, postalCode);
    }

    @Override
    public String toString() {
        return "Address{" +
                " streetName='" + streetName + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", postalCode=" + postalCode +
                '}';
    }
}
