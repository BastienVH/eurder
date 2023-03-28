package com.bastienvh.eurder.domain;

import java.util.UUID;

public record CustomerDTO(UUID id, String firstName, String lastName, String email, Address address, String phoneNumber) {
}
