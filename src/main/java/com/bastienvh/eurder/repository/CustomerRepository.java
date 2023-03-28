package com.bastienvh.eurder.repository;

import com.bastienvh.eurder.domain.Customer;
import com.bastienvh.eurder.exceptions.InvalidCustomerException;
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

    public void createCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    public Collection<Customer> getAllCustomers() {
        return customers.values();
    }

    public Customer getCustomerById(UUID id) {
        if (customers.containsKey(id)) {
            return customers.get(id);
        } else {
            throw new InvalidCustomerException("No user found with id: " + id);
        }
    }
}
