package com.solvd.persistence.persons.customer;

import com.solvd.model.persons.customer.Customer;
import com.solvd.persistence.utilities.Repositorable;

import java.util.Optional;

public interface CustomerRepository extends Repositorable<Customer> {
    void create(Customer customer);

    Optional<Customer> findById(Long id);

    Optional<Customer> findByRentalDealId(Long id);

}
