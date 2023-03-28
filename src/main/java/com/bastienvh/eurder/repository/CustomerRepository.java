package com.bastienvh.eurder.repository;

import com.bastienvh.eurder.domain.Customer;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
@Repository
public class CustomerRepository {
    ConcurrentHashMap<UUID, Customer> customers;

    public CustomerRepository() {
        customers = new ConcurrentHashMap<>();
    }

    public UUID createCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
        return customer.getId();
    }

    public Collection<Customer> getAllCustomers() {
        return customers.values();
    }
}
