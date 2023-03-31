package com.bastienvh.eurder.domain.customer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCustomerDTO(@NotBlank(message = "first name is mandatory") String firstName,
                                @NotBlank(message = "last name is mandatory") String lastName,
                                @NotBlank(message = "email is mandatory") String email, @NotNull @Valid Address address,
                                String phoneNumber) {

}
