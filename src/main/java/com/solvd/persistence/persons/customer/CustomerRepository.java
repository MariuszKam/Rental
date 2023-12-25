package com.solvd.persistence.persons.customer;

import com.solvd.model.persons.customer.Customer;

import java.util.Optional;

public interface CustomerRepository {
    void create(Customer customer);
    Optional<Customer> findById(Long id);

}
