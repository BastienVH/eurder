package com.bastienvh.eurder.unittests;


import com.bastienvh.eurder.domain.customer.Address;
import org.junit.jupiter.api.Test;

class AddressTest {
    @Test
    void addAddress_withValidInputs_thenNoExceptionIsThrown() {
        new Address("Kantersteen", "2A",  1000);
    }
}