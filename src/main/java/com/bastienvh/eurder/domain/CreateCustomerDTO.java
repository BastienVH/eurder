package com.bastienvh.eurder.domain;

import java.util.UUID;

public record CreateCustomerDTO(UUID id, String firstName, String lastName, String email, AddressDTO address, String phoneNumber) {
}
