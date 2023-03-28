package com.bastienvh.eurder.domain;

public record CreateCustomerDTO(String firstName, String lastName, String email, Address address, String phoneNumber) {
}
