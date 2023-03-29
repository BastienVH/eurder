package com.bastienvh.eurder.domain.customer;

public record CreateCustomerDTO(String firstName, String lastName, String email, Address address, String phoneNumber) {
}
