package com.bastienvh.eurder.domain.customer;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record Address(@NotBlank(message = "street name not provided") String street,
                      @NotBlank(message = "number not provided") String number,
                      @NotBlank(message = "city name not provided") String city,
                      @Min(value = 1000, message = "postal code not provided or below minimum threshold (1000)")
                      @Max(value = 9999, message = "postal code is above maximum threshold (9999)") int postalCode) {
}
