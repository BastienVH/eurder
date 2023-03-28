package com.bastienvh.eurder.service;

import com.bastienvh.eurder.domain.CreateCustomerDTO;
import com.bastienvh.eurder.domain.CustomerDTO;
import com.bastienvh.eurder.domain.CustomerMapper;
import com.bastienvh.eurder.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {
    CustomerRepository repository;
    CustomerMapper mapper;

    public CustomerService(CustomerRepository repository, CustomerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UUID createCustomer(CreateCustomerDTO createCustomerDTO) {
        return repository.createCustomer(mapper.createDTOToCustomer(createCustomerDTO));
    }

    public List<CustomerDTO> getAllCustomers() {
        return repository.getAllCustomers().stream()
                .map(customer -> mapper.customerToDTO(customer))
                .toList();
    }
}
