package com.bastienvh.eurder.domain;

import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerDTO customerToDTO(Customer customer) {
        return new CustomerDTO(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getAddress(), customer.getPhoneNumber());
    }

    public Customer createDTOToCustomer(CreateCustomerDTO createCustomerDTO) {
        return new Customer(createCustomerDTO.firstName(), createCustomerDTO.lastName(), createCustomerDTO.email(), createCustomerDTO.address(), createCustomerDTO.phoneNumber());
    }

}
