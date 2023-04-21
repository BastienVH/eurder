package com.bastienvh.eurder.unittests;

import com.bastienvh.eurder.api.CustomerController;
import com.bastienvh.eurder.domain.customer.Address;
import com.bastienvh.eurder.domain.customer.CustomerDTO;
import com.bastienvh.eurder.service.CustomerService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {
    @Mock
    CustomerService service;
    @InjectMocks
    CustomerController controller;
    
    private final CustomerDTO customerDTO = new CustomerDTO(
            UUID.randomUUID(),
            "firstname",
            "lastName",
            "first.last@example.com",
            new Address("street", "number",  1000),
            "phone number");

    @Test
    void getOneCustomer_withExistingUUID_thenReturnExistingUser() {
        //GIVEN
        when(service.getCustomerById(customerDTO.id())).thenReturn(customerDTO);
        //WHEN
        CustomerDTO actual = controller.getCustomerById(customerDTO.id());
        //THEN
        Assertions.assertThat(actual).isEqualTo(customerDTO);
    }
}