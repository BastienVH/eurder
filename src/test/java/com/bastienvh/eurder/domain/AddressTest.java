package com.bastienvh.eurder.domain;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AddressTest {
    @Test
    void addAddress_withValidInputs_thenNoExceptionIsThrown() {
        new Address("Kantersteen", "2A", "Brussel", 1000);
    }

    @Test
    void addAddress_withInvalidPostalCode_thenExceptionIsThrown() {
        Assertions.assertThatThrownBy(() -> new Address("Kantersteen", "2A", "Brussel", 5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("postal code is not valid");
    }

    @Test
    void addAddress_withNullStreet_thenExceptionIsThrown() {
        Assertions.assertThatThrownBy(() -> new Address(null, "2A", "Brussel", 1000))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("street can't be null");
    }
}