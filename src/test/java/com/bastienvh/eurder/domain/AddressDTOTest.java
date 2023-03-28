package com.bastienvh.eurder.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AddressDTOTest {
    @Test
    void addAddressDTO_withValidInputs_thenNoExceptionIsThrown() {
        //GIVEN
        AddressDTO address = new AddressDTO("Kantersteen", "2A", "Brussel", 1000);
    }

    @Test
    void addAddressDTO_withInvalidPostalCode_thenExceptionIsThrown() {
        Assertions.assertThatThrownBy(() -> new AddressDTO("Kantersteen", "2A", "Brussel", 5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("postal code is not valid");
    }

    @Test
    void addAddressDTO_withNullStreet_thenExceptionIsThrown() {
        Assertions.assertThatThrownBy(() -> new AddressDTO(null, "2A", "Brussel", 1000))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("street can't be null");
    }

}