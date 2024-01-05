package com.solvd.persistence.mybatis;


import com.solvd.model.persons.customer.Customer;
import com.solvd.persistence.persons.customer.CustomerRepository;
import com.solvd.persistence.utilities.RepositoryUtility;

import java.util.Optional;

public class CustomerRepositoryImpl implements CustomerRepository {

    private static final Class<CustomerRepository> REPOSITORY_CLASS = CustomerRepository.class;

    @Override
    public void create(Customer customer) {
        RepositoryUtility.executeVoidSQL(REPOSITORY_CLASS, customerRepository -> customerRepository.create(customer));
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return RepositoryUtility.executeTypeSQL(REPOSITORY_CLASS, customerRepository -> customerRepository.findById(id));
    }

    @Override
    public Optional<Customer> findByRentalDealId(Long rentalDealId) {
        return RepositoryUtility.executeTypeSQL(REPOSITORY_CLASS, customerRepository -> customerRepository.findByRentalDealId(rentalDealId));
    }

}