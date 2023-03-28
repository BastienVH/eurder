package com.bastienvh.eurder.api;

import com.bastienvh.eurder.domain.CreateCustomerDTO;
import com.bastienvh.eurder.domain.CustomerDTO;
import com.bastienvh.eurder.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createCustomer(@RequestBody CreateCustomerDTO createCustomerDTO) {
        return service.createCustomer(createCustomerDTO);
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDTO> getAllCustomers() {
        return service.getAllCustomers();
    }
}
