package com.solvd.service.persons.customer;

import com.solvd.model.exception.ItemNotFoundException;
import com.solvd.model.persons.customer.Customer;
import com.solvd.persistence.persons.customer.CustomerRepository;
import com.solvd.persistence.mybatis.CustomerRepositoryImpl;

public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository = new CustomerRepositoryImpl();

    @Override
    public Customer create(Customer customer) {
        customer.setId(null);
        customerRepository.create(customer);
        return customer;
    }

    @Override
    public Customer loadCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Customer"));
    }

    @Override
    public Customer loadCustomerByRentalDealId(Long id) {
        return customerRepository.findByRentalDealId(id).orElseThrow(() -> new ItemNotFoundException("Customer"));
    }


}
