package com.bastienvh.eurder.service;

import com.bastienvh.eurder.domain.customer.CreateCustomerDTO;
import com.bastienvh.eurder.domain.customer.Customer;
import com.bastienvh.eurder.domain.customer.CustomerDTO;
import com.bastienvh.eurder.domain.customer.CustomerMapper;
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

    public CustomerDTO createCustomer(CreateCustomerDTO createCustomerDTO) {
        Customer customer = mapper.createDTOToCustomer(createCustomerDTO);
        repository.createCustomer(customer);
        return mapper.customerToDTO(customer);
    }


    public List<CustomerDTO> getAllCustomers() {
        return repository.getAllCustomers().stream()
                .map(customer -> mapper.customerToDTO(customer))
                .toList();
    }

    public CustomerDTO getCustomerById(UUID id) {
        return mapper.customerToDTO(repository.getCustomerById(id));
    }
}
