package com.bastienvh.eurder.service;

import com.bastienvh.eurder.domain.customer.CreateCustomerDTO;
import com.bastienvh.eurder.domain.customer.Customer;
import com.bastienvh.eurder.domain.customer.CustomerDTO;
import com.bastienvh.eurder.domain.customer.CustomerMapper;
import com.bastienvh.eurder.exceptions.InvalidCustomerException;
import com.bastienvh.eurder.repository.AddressRepository;
import com.bastienvh.eurder.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {
    private final CustomerRepository repository;
    private final CustomerMapper mapper;
    private final AddressRepository addressRepository;

    public CustomerService(CustomerRepository repository, CustomerMapper mapper, AddressRepository addressRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.addressRepository = addressRepository;
    }

    public CustomerDTO createCustomer(CreateCustomerDTO createCustomerDTO) {
        Customer customer = mapper.createDTOToCustomer(createCustomerDTO);
        repository.save(customer);
//        addressRepository.save(createCustomerDTO.address());
        return mapper.customerToDTO(customer);
    }


    public List<CustomerDTO> getAllCustomers() {
        return repository.findAll().stream()
                .map(mapper::customerToDTO)
                .toList();
    }

    public CustomerDTO getCustomerById(UUID id) {
        return mapper.customerToDTO(repository.findById(id).orElseThrow(() -> new InvalidCustomerException("No user found with id: " + id)));
    }
}
